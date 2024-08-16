package com.example.hashmap_analyzer.domain;

import jakarta.persistence.*;
import jakarta.persistence.criteria.CriteriaBuilder;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)

public class URLClass {
    @Id
    private Integer Hash;
    @Column(name="url", unique = true)
    private String URL;
    @Column(name="html_code", nullable = false)
    private String html;

    public URLClass() {

    }
    public URLClass(String URL, String html)
    {
        this.URL = URL;
        this.html = html;
        this.Hash=URL.hashCode();
    }

    public String getURL() {
        return URL;
    }

    public String getHtml() {
        return html;
    }
    public Integer getHash()
    {
        return Hash;
    }

    public void setHtml(String html) {
        this.html = html;
    }

    public void setHash(Integer hash) {
        Hash = hash;
    }

    public void SetHtml(String html){
        this.html = html;
    }

    public void setURL(String URL) {
        this.URL = URL;
        Hash=URL.hashCode();
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
