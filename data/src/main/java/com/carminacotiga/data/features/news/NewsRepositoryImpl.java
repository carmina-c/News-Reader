package com.carminacotiga.data.features.news;

import com.carminacotiga.data.NewsRepository;
import com.carminacotiga.data.features.news.model.Article;
import com.carminacotiga.data.features.news.remote.NewsRemoteSource;

import java.util.List;

import io.reactivex.Single;
import io.reactivex.annotations.NonNull;

public class NewsRepositoryImpl implements NewsRepository {
    private final NewsRemoteSource remoteSource;

    public NewsRepositoryImpl(NewsRemoteSource remoteSource) {
        this.remoteSource = remoteSource;
    }

    @Override
    @NonNull
    public Single<List<Article>> getNewsArticles() {
        return remoteSource.getNewsArticles();
    }
}
