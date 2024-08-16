package com.example.hashmap_analyzer.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name="hash_after")
public class URLClassAfter extends URLClass{

    public URLClassAfter()
    {

    }
    public URLClassAfter(String URL, String Html){
        this.setURL(URL);
        this.setHtml(Html);
    }
}
