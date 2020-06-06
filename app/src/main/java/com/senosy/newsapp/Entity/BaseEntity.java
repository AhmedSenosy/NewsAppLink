package com.senosy.newsapp.Entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class BaseEntity<T> {

    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("source")
    @Expose
    private String source;
    @SerializedName("sortBy")
    @Expose
    private String sortBy;
    @SerializedName("articles")
    @Expose
    private T articles;

    public BaseEntity(String status, String source, String sortBy, T articles) {
        this.status = status;
        this.source = source;
        this.sortBy = sortBy;
        this.articles = articles;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getSortBy() {
        return sortBy;
    }

    public void setSortBy(String sortBy) {
        this.sortBy = sortBy;
    }

    public T getArticles() {
        return articles;
    }

    public void setArticles(T articles) {
        this.articles = articles;
    }

}
