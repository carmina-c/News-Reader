package com.carminacotiga.newsreader.ui.feature.model.mapper;

import com.carminacotiga.data.features.news.model.Article;
import com.carminacotiga.newsreader.ui.feature.model.ArticleItemViewModel;

import java.util.ArrayList;
import java.util.List;

public class ArticlesToVMListMapper implements io.reactivex.functions.Function<List<Article>, List<ArticleItemViewModel>> {

    @Override
    public List<ArticleItemViewModel> apply(List<Article> articles) {
        List<ArticleItemViewModel> vmItems = new ArrayList<>();

        for (Article current : articles) {
            ArticleItemViewModel viewModelItem = new ArticleItemViewModel(current.id, current.title, current.content, current.imageUrl);

            vmItems.add(viewModelItem);
        }

        return vmItems;
    }
}
