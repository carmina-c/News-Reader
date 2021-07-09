package com.carminacotiga.data;

import com.carminacotiga.data.features.news.local.NewsLocalSource;
import com.carminacotiga.data.features.news.model.Article;
import com.carminacotiga.data.features.news.remote.NewsRemoteSource;

import java.util.List;

import io.reactivex.Single;
import io.reactivex.SingleSource;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

public class NewsRepositoryImpl implements NewsRepository {
    private final NewsRemoteSource remoteSource;
    private final NewsLocalSource localSource;

    public NewsRepositoryImpl(NewsRemoteSource remoteSource, NewsLocalSource localSource) {
        this.remoteSource = remoteSource;
        this.localSource = localSource;
    }

    @Override
    @NonNull
    public Single<List<Article>> getNewsArticles() {
        return remoteSource.getNewsArticles()
                .flatMap((Function<List<Article>, SingleSource<List<Article>>>) articles ->
                        localSource.saveArticles(articles)
                                .andThen(localSource.fetchData()))
                .onErrorResumeNext(localSource.fetchData());
    }

    @Override
    public Single<Article> getArticle(int id) {
        return localSource.getArticle(id)
                .subscribeOn(Schedulers.io());
    }
}
