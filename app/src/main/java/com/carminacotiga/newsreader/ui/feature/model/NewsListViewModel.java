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
    private Disposable disposable;

    public NewsListViewModel(Application application, NewsRepository repo) {
        super(application);
        this.repo = repo;
        this.error = new SingleLiveEvent<>();
        this.openLink = new SingleLiveEvent<>();
        this.newsList = new ObservableArrayList<>();
    }

    @SuppressLint("CheckResult")
    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    public void refresh() {
        /* newsList.add(new ArticleItemViewModel("Malaiesti", "Cabana Malaiesti situata la altitudine de 1720m, in Valea Malaesti, cea mai frumoasa vale din Bucegi strajuita de Bucsoiu si Padina Crucii.", "https://i.ytimg.com/vi/hIUq91Y4wEE/maxresdefault.jpg"));
        newsList.add(new ArticleItemViewModel("Omu", "Cabana Omu situata pe varful Omu din Muntii Bucegi este una din cele mai inalte cabane montane din Carpatii Romanesti!", "https://www.greuladeal.ro/content/images/2020/11/cabana-omu-apus.jpg"));
        newsList.add(new ArticleItemViewModel("Sambata de sus", "Cabana Valea Sambetei situata pe Valea Sambetei din Muntii Fagaras este un punct de plecare catre varful Moldoveanu cel mai inalt varf din Carpati.", "https://calatoriileioanei.ro/wp-content/uploads/2016/04/Cabana-Valea-S%C3%A2mbetei-cover.jpg"));*/

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
