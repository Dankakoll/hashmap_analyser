package com.example.hashmap_analyzer.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "hash_before")
public class URLClassbefore extends URLClass {
    public URLClassbefore(String URL, String Html){
        this.setURL(URL);
        this.setHtml(Html);
        this.setHash(URL.hashCode());
    }

    public URLClassbefore() {

    }
}
