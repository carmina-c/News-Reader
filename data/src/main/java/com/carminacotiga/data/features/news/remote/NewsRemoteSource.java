package com.carminacotiga.data.features.news.remote;

import com.carminacotiga.data.features.news.model.Article;
import com.carminacotiga.data.features.news.remote.mapper.NewsDtoToNewsMapper;
import com.carminacotiga.data.remote.NewsApi;

import java.util.List;

import io.reactivex.Single;
import io.reactivex.annotations.NonNull;
import io.reactivex.schedulers.Schedulers;

public class NewsRemoteSource {
    private static final String API_KEY = "aff82e681dac414d9ca0e135c742cf37";
    private static final String EN_LANGUAGE_FILTER = "en";
    @NonNull
    private final NewsApi newsApi;

    public NewsRemoteSource(NewsApi newsApi) {
        this.newsApi = newsApi;
    }

    public Single<List<Article>> getNewsArticles() {
        return newsApi.getNewsArticles(API_KEY, EN_LANGUAGE_FILTER)
                .subscribeOn(Schedulers.io())
                .map(new NewsDtoToNewsMapper());
    }
}
