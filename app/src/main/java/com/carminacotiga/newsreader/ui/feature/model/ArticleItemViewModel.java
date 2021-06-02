package com.carminacotiga.newsreader.ui.feature.model;

import androidx.annotation.Nullable;


public class ArticleItemViewModel {
    public final String articleTitle;
    public final String articleContent;
    public final String articleImageURL;
    @Nullable
    public Integer id;

    public ArticleItemViewModel(String title, String content, String imageURL) {
        this.articleTitle = title;
        this.articleContent = content;
        this.articleImageURL = imageURL;
    }
}
