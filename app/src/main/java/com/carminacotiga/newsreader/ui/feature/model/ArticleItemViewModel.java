package com.carminacotiga.newsreader.ui.feature.model;

import androidx.annotation.NonNull;


public class ArticleItemViewModel {
    public final String articleTitle;
    public final String articleContent;
    public final String articleImageURL;
    @NonNull
    public final Integer id;

    public ArticleItemViewModel(int id, String title, String content, String imageURL) {
        this.id = id;
        this.articleTitle = title;
        this.articleContent = content;
        this.articleImageURL = imageURL;
    }
}
