package com.carminacotiga.newsreader.ui.feature.model;

import android.annotation.SuppressLint;
import android.app.Application;

import androidx.annotation.NonNull;
import androidx.databinding.ObservableArrayList;
import androidx.databinding.ObservableList;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.OnLifecycleEvent;

import com.carminacotiga.data.NewsRepository;
import com.carminacotiga.newsreader.ui.feature.listener.ArticleHandler;
import com.carminacotiga.newsreader.ui.feature.model.mapper.ArticlesToVMListMapper;
import com.carminacotiga.newsreader.ui.feature.reactive.SingleLiveEvent;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;

public class NewsListViewModel extends AndroidViewModel implements LifecycleObserver, ArticleHandler {

    private final static String LINK = "https://newsapi.org/";
    public final SingleLiveEvent<Throwable> error;
    public final SingleLiveEvent<String> openLink;
    public final ObservableList<ArticleItemViewModel> newsList;
    private final NewsRepository repo;
    public SingleLiveEvent<ArticleItemViewModel> openArticle;
    private Disposable disposable;

    public NewsListViewModel(Application application, NewsRepository repo) {
        super(application);
        this.repo = repo;
        this.error = new SingleLiveEvent<>();
        this.openLink = new SingleLiveEvent<>();
        this.newsList = new ObservableArrayList<>();
        this.openArticle = new SingleLiveEvent<>();
    }

    @SuppressLint("CheckResult")
    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    public void refresh() {
        if (newsList.isEmpty()) {
            disposable = repo.getNewsArticles()
                    .map(new ArticlesToVMListMapper())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(
                            this::onNewsArticlesReceived,
                            this::onNewsArticlesError);
        }
    }

    private void onNewsArticlesReceived(@NonNull List<ArticleItemViewModel> articles) {
        newsList.addAll(articles);
    }

    private void onNewsArticlesError(Throwable throwable) {
        error.setValue(throwable);
    }

    @Override
    public void onItemSelected(ArticleItemViewModel item) {
        openArticle.setValue(item);
    }

    public void onPoweredBySelected() {
        openLink.setValue(LINK);
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        if (disposable != null) {
            disposable.dispose();
        }
    }
}
