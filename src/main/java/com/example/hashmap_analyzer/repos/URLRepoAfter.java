package com.example.hashmap_analyzer.repos;

import com.example.hashmap_analyzer.domain.URLClass;
import com.example.hashmap_analyzer.domain.URLClassAfter;
import com.example.hashmap_analyzer.domain.URLClassbefore;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface URLRepoAfter extends JpaRepository <URLClassAfter,Long> {
// Запрос на вывод удаленных записей
@Query(value = "Select u2.URL from URLClassbefore u2 where u2.URL not in (Select u1.URL from URLClassAfter u1)")
List<String> ShowDeleted();
// Запрос на вывод измененных записей
@Query(value = "Select u1.URL from URLClassAfter u1, URLClassbefore u2 where u1.Hash=u2.Hash and not u1.html = u2.html")
List<String> ShowChanged();
// Запрос на вывод добавленных записей
@Query(value = "Select u1.URL from URLClassAfter u1 where u1.URL not in (select URL from URLClassbefore)")
List<String> ShowAdded();
}
