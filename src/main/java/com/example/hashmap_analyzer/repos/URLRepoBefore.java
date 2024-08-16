package com.example.hashmap_analyzer.repos;

import com.example.hashmap_analyzer.domain.URLClass;
import com.example.hashmap_analyzer.domain.URLClassbefore;
import org.hibernate.annotations.SQLInsert;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface URLRepoBefore extends JpaRepository <URLClassbefore,Long> {
// Обновление данных
@Modifying
@Query(value = "update hash_before set html_code = :html_code where hash = :hash",nativeQuery = true)
public void update(Integer hash, String html_code);
}
