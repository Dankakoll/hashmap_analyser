package com.example.hashmap_analyzer.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface URLRepo extends JpaRepository <URLClass,Long> {
}
