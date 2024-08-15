package com.example.hashmap_analyzer;

import com.example.hashmap_analyzer.domain.URLRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {

    private URLRepo repo_before;
    private URLRepo repo_today;

    @Autowired
    Controller(URLRepo repo_before , URLRepo repo_today)
    {
        this.repo_before=repo_before;
        this.repo_today=repo_today;
    }
    @GetMapping("/message")
    ResponseEntity<String> message()
    {
         return new ResponseEntity<>( "Здравствуйте, дорогая и.о. секретаря За последние сутки во вверенных Вам сайтах произошли следующие изменения:", HttpStatus.OK);
    }

}
