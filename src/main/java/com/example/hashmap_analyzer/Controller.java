package com.example.hashmap_analyzer;

import com.example.hashmap_analyzer.domain.URLClass;
import com.example.hashmap_analyzer.domain.URLClassAfter;
import com.example.hashmap_analyzer.domain.URLClassbefore;
import com.example.hashmap_analyzer.repos.URLRepoAfter;
import com.example.hashmap_analyzer.repos.URLRepoBefore;
import org.hibernate.Length;
import org.hibernate.dialect.function.LengthFunction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.print.DocFlavor;
import java.util.*;

@RestController
public class Controller {

    private final URLRepoBefore repo_before;
    private final URLRepoAfter repo_after;

    @Autowired
    Controller(URLRepoBefore repo_before , URLRepoAfter repo_after)
    {
        this.repo_before=repo_before;
        this.repo_after=repo_after;
    }
    //Вывод сообщения
    @GetMapping("/message")
    ResponseEntity<String> message()
    {
        //Выводит различия между таблицами
        List<List<String>> answer = compareTables();
        List<String> responses =new ArrayList<>();
        responses.add("Здравствуйте, дорогая и.о. секретаря."+"<br>"+"За последние сутки во вверенных Вам сайтах произошли следующие изменения:"+"<br>");
        responses.add("<br>"+"Исчезли следующие страницы: ");
        responses.add("<br>"+"Появились следующие новые страницы: ");
        responses.add("<br>"+"Изменились следующие страницы: ");
        responses.add("<br>"+"С уважением,"+"<br>" +
                "автоматизированная система"+"<br>" +
                "мониторинга.");
        Iterator<List<String>> itans = answer.iterator();
        Iterator<String> itresp = responses.iterator();
        String text= itresp.next();
        while (itans.hasNext())
        {
            List<String> next=itans.next();
            if (next.size()>0)
            text+=itresp.next()+next.toString().replace("[","").replace("]","").trim()
                    +"<br>";
            else
            text+=itresp.next() + "Нет изменений"+"<br>";
        }
        text+=itresp.next();
        return new ResponseEntity<>(text,HttpStatus.OK);
    }
    @GetMapping("/before/{Id}")
    Optional<URLClassbefore> findbyHashBefore (@PathVariable Long Id)
    {
        Optional<URLClassbefore> urlClass = repo_before.findById(Id);
        return urlClass;
    }
    @GetMapping ("/after/{Id}")
    Optional<URLClassAfter> findbyHashAfter (@PathVariable Long Id)
    {
        Optional<URLClassAfter> urlClassAfter = repo_after.findById(Id);
        return urlClassAfter;
    }
    @GetMapping("/comp")
    List<List<String>> compareTables()
    {
        List<String> changed = repo_after.ShowChanged();
        List<String> added = repo_after.ShowAdded();
        List<String> deleted = repo_after.ShowDeleted();

        return Arrays.asList(deleted,added,changed);
    }
    @PostMapping(value = "/add",consumes = "application/json")
    ResponseEntity<?> new_url (@RequestBody URLClassAfter after)
    {
        repo_after.save(after);
        return new ResponseEntity<>("Added new entry with URL"+ after.getURL(),HttpStatus.OK);
    }
    @PutMapping(value="/change", consumes = "application/json")
    ResponseEntity<?> change_html (@RequestBody URLClassAfter after)
    {
            URLClassAfter updated = repo_after.findById(after.getHash().longValue()).map((found)->
                    {
                        found.setHtml(after.getHtml());
                        found.setURL(after.getURL());
                        return repo_after.save(found);
                    }
                    ).orElseGet(()-> {
                        after.setURL(after.getURL());
                        return repo_after.save(after);
                                });
            return new ResponseEntity<>("Changed HTML of URL " + updated.getURL(), HttpStatus.OK);
    }
    //Копирование таблицы текущего дня в предыдущий
    @GetMapping("/copy")
    @Transactional
    ResponseEntity<?> copy_to_tablebefore ()
    {
        List<URLClassAfter>itm =repo_after.findAll();
        Iterator<URLClassAfter> it =itm.iterator();
        if (itm.size()>0) {
            while (it.hasNext()) {
                URLClassAfter next=it.next();
                URLClassbefore elem = new URLClassbefore(next.getURL(),next.getHtml());
                if (repo_before.findById(elem.getHash().longValue()).isPresent()) {
                    repo_before.update(elem.getHash(), elem.getHtml());
                } else {
                    repo_before.save(elem);
                    }
            }
            return new ResponseEntity<>("copied", HttpStatus.OK);
        }
        else
        {
            return new ResponseEntity<>("Nothing to copy",HttpStatus.BAD_GATEWAY);
        }

    }
}
