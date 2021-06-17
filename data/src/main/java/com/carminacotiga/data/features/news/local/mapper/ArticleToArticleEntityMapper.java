package com.carminacotiga.data.features.news.local.mapper;

import com.carminacotiga.data.features.news.local.ArticleEntity;
import com.carminacotiga.data.features.news.model.Article;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.functions.Function;


public class ArticleToArticleEntityMapper implements Function<List<Article>, List<ArticleEntity>> {

    private final static String DEFAULT_TITLE = "Default title";
    private final static String DEFAULT_CONTENT = "Default content";

    @Override
    public List<ArticleEntity> apply(List<Article> articles) {
        List<ArticleEntity> articleEntities = new ArrayList<>();
        for (Article currentArticle : articles) {
            ArticleEntity entity = new ArticleEntity();
            entity.setTitle(!currentArticle.title.equals("") ? currentArticle.title : DEFAULT_TITLE);
            entity.setContent(!currentArticle.content.equals("") ? currentArticle.content : DEFAULT_CONTENT);
            entity.setPhoto(currentArticle.imageUrl);

            articleEntities.add(entity);
        }

        return articleEntities;
    }
}
