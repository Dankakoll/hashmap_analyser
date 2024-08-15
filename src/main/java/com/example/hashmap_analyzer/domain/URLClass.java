package com.example.hashmap_analyzer.domain;

import jakarta.persistence.*;

import java.util.HashMap;

@Entity(name="URL")
public class URLClass {
    @Id
    private Long Hash;
    @Column(name="html_code", nullable = false)
    private String html;

    public URLClass() {

    }
    public URLClass(Long Hash, String html)
    {
        this.html = html;
    }

    public Long getHash() {
        return Hash;
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }
}
