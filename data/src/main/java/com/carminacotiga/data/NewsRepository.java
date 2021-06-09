package com.carminacotiga.data;

import androidx.annotation.NonNull;

import com.carminacotiga.data.features.news.model.Article;

import java.util.List;

import io.reactivex.Single;

public interface NewsRepository {
    @NonNull
    Single<List<Article>> getNewsArticles();
}
