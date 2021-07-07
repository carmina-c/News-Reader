package com.carminacotiga.newsreader;

import android.app.Application;

import com.carminacotiga.data.di.RepoModule;

public class NewsReaderApplication extends Application {
    //move along, will be replaced with Dagger later
    private static RepoModule repoModule;

    public static RepoModule getRepoProvider() {
        return repoModule;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        repoModule = new RepoModule(this);
    }
}
