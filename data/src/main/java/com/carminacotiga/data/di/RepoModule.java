package com.carminacotiga.data.di;

import android.app.Application;
import android.content.Context;

import androidx.room.Room;

import com.carminacotiga.data.NewsRepository;
import com.carminacotiga.data.NewsRepositoryImpl;
import com.carminacotiga.data.features.news.local.NewsLocalSource;
import com.carminacotiga.data.features.news.local.store.ArticleDatabase;
import com.carminacotiga.data.features.news.remote.NewsRemoteSource;
import com.carminacotiga.data.remote.HttpClientFactory;

import io.reactivex.annotations.NonNull;

public class RepoModule {
    @NonNull
    private final Context context;
    @NonNull
    private final HttpClientFactory httpClientFactory;

    private volatile ArticleDatabase database;

    public RepoModule(@NonNull Application application) {
        this.context = application.getApplicationContext();
        this.httpClientFactory = new HttpClientFactory();
    }

    public NewsRepository provideNewsRepository() {
        return new NewsRepositoryImpl(provideNewsRemoteSource(), provideLocalDataStore());
    }

    private NewsRemoteSource provideNewsRemoteSource() {
        return new NewsRemoteSource(httpClientFactory.getNewsApi());
    }

    NewsLocalSource provideLocalDataStore() {
        ArticleDatabase database = getInstance();
        return new NewsLocalSource(database.articlesDao());
    }

    ArticleDatabase getInstance() {
        if (database == null) {
            synchronized (ArticleDatabase.class) {
                if (database == null) {
                    database = Room.databaseBuilder(context.getApplicationContext(),
                            ArticleDatabase.class, "Sample.db")
                            .build();
                }
            }
        }
        return database;
    }
}
