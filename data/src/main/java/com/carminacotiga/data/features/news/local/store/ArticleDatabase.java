package com.carminacotiga.data.features.news.local.store;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.carminacotiga.data.features.news.local.ArticleEntity;
import com.carminacotiga.data.features.news.local.ArticlesDao;

@Database(entities = {ArticleEntity.class}, version = 1)
public abstract class ArticleDatabase extends RoomDatabase {
    public abstract ArticlesDao articlesDao();
}
