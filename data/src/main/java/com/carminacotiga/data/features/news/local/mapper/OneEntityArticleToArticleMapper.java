package com.carminacotiga.data.features.news.local.mapper;

import com.carminacotiga.data.features.news.local.ArticleEntity;
import com.carminacotiga.data.features.news.model.Article;

import org.jetbrains.annotations.NotNull;

import io.reactivex.functions.Function;

public class OneEntityArticleToArticleMapper implements Function<ArticleEntity, Article> {

    @Override
    public Article apply(@NotNull ArticleEntity articleEntity) throws Exception {
        Article article = new Article(articleEntity.getId(), articleEntity.getPhoto(), articleEntity.getTitle(), articleEntity.getContent(), "");

        return article;
    }
}