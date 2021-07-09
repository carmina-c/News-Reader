package com.carminacotiga.data.features.news.local;

import com.carminacotiga.data.features.news.local.mapper.ArticleEntityToArticleMapper;
import com.carminacotiga.data.features.news.local.mapper.ArticleToArticleEntityMapper;
import com.carminacotiga.data.features.news.local.mapper.OneEntityArticleToArticleMapper;
import com.carminacotiga.data.features.news.model.Article;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Single;
import io.reactivex.schedulers.Schedulers;

public class NewsLocalSource {
    private final ArticlesDao dao;

    public NewsLocalSource(ArticlesDao dao) {
        this.dao = dao;
    }

    public Single<List<ArticleEntity>> getArticlesList() {
        return dao.queryArticles();
    }

    public Single<Article> getArticle(int id) {
        return dao.queryArticleItem(id).map(new OneEntityArticleToArticleMapper());
    }

    public Completable insert(ArticleEntity article) {
        return dao.insertArticle(article);
    }

    public Single<List<Article>> fetchData() {
        return getArticlesList().map(new ArticleEntityToArticleMapper());
    }

    public Completable saveArticle(ArticleEntity article) {
        if (article.id == null) {
            return dao.insertArticle(article);
        } else {
            return dao.updateFeed(article.title, article.content, article.photo, article.id);
        }
    }

    public Completable saveArticles(List<Article> articles) {

        return dao.deleteAllArticles()
                .andThen(
                        Single.just(articles)
                                .map(new ArticleToArticleEntityMapper())
                                .flatMapCompletable(dao::insertArticles)
                ).subscribeOn(Schedulers.io());
    }
}