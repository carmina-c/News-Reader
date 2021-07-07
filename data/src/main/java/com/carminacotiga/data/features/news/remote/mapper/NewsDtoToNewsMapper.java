package com.carminacotiga.data.features.news.remote.mapper;

import com.carminacotiga.data.features.news.model.Article;
import com.carminacotiga.data.features.news.remote.model.ArticleDto;
import com.carminacotiga.data.features.news.remote.model.ArticleListDto;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.functions.Function;

public class NewsDtoToNewsMapper implements Function<ArticleListDto, List<Article>> {

    @Override
    public List<Article> apply(ArticleListDto articleDtos) {
        List<Article> articles = new ArrayList<>();

        for (ArticleDto dto : articleDtos.articles) {
            Article article = new Article(
                    dto.urlToImage != null ? dto.urlToImage : "", //Adding default values for business model
                    dto.title != null ? dto.title : "",
                    dto.content != null ? dto.content : "",
                    dto.description != null ? dto.description : ""
            );

            articles.add(article);
        }

        return articles;
    }

}