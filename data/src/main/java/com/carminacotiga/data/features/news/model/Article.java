package com.carminacotiga.data.features.news.model;

import io.reactivex.annotations.NonNull;

public class Article {
    @NonNull
    public final int id;
    @NonNull
    public final String imageUrl;
    @NonNull
    public final String title;
    @NonNull
    public final String content;
    @NonNull
    public final String description;

    public Article(@NonNull int id, @NonNull String imageUrl, @NonNull String title, @NonNull String content, @NonNull String description) {
        this.id = id;
        this.imageUrl = imageUrl;
        this.title = title;
        this.content = content;
        this.description = description;
    }
}
