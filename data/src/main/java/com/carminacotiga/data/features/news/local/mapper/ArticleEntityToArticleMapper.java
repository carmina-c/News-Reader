package com.carminacotiga.data.features.news.local.mapper;

import com.carminacotiga.data.features.news.local.ArticleEntity;
import com.carminacotiga.data.features.news.model.Article;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.functions.Function;

public class ArticleEntityToArticleMapper implements Function<List<ArticleEntity>, List<Article>> {

    @Override
    public List<Article> apply(@NotNull List<ArticleEntity> articleEntities) throws Exception {
        List<Article> articleList = new ArrayList<>();
        for (ArticleEntity current : articleEntities) {
            Article article = new Article(current.getPhoto(), current.getTitle(), current.getContent(), "");
            articleList.add(article);
        }
        return articleList;
    }
}
