package com.carminacotiga.newsreader.ui.feature.model;

import android.annotation.SuppressLint;
import android.util.Log;

import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.ViewModel;

import com.carminacotiga.data.NewsRepository;
import com.carminacotiga.data.features.news.model.Article;

public class ArticleDetailsViewModel extends ViewModel implements LifecycleObserver {

    private static final String TAG = ArticleDetailsViewModel.class.getSimpleName();
    private final NewsRepository repository;
    public String title;
    public String content;
    public String articleImageURL;
    Article newsArticle;

    public ArticleDetailsViewModel(NewsRepository repository) {
        this.repository = repository;
    }

    @SuppressLint("CheckResult")
    public void initArticle(int id) {
        repository.getArticle(id)
                .subscribe(
                        this::onArticleReceived,
                        this::onArticleError
                );
    }

    private void onArticleError(Throwable throwable) {
        Log.e(TAG, "Eroare la deschiderea articolului: " + throwable.getMessage());
    }

    private void onArticleReceived(Article article) {
        newsArticle = article;
        title = article.title;
        content = article.content;
        articleImageURL = article.imageUrl;
    }
}