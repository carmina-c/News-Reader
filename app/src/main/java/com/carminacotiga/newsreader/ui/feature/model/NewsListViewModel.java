package com.carminacotiga.newsreader.ui.feature.model;

import androidx.annotation.NonNull;
import androidx.databinding.ObservableArrayList;
import androidx.databinding.ObservableList;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.OnLifecycleEvent;
import androidx.lifecycle.ViewModel;

import com.carminacotiga.newsreader.ui.feature.listener.ArticleHandler;

public class NewsListViewModel extends ViewModel implements LifecycleObserver, ArticleHandler {

    private static final String TAG = NewsListViewModel.class.getName();

    @NonNull
    public final ObservableList<ArticleItemViewModel> newsList = new ObservableArrayList<>();

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    public void refresh() {
        if (newsList.isEmpty()) {
            newsList.add(new ArticleItemViewModel("Malaiesti", "Cabana Malaiesti situata la altitudine de 1720m, in Valea Malaesti, cea mai frumoasa vale din Bucegi strajuita de Bucsoiu si Padina Crucii.", "https://i.ytimg.com/vi/hIUq91Y4wEE/maxresdefault.jpg"));
            newsList.add(new ArticleItemViewModel("Omu", "Cabana Omu situata pe varful Omu din Muntii Bucegi este una din cele mai inalte cabane montane din Carpatii Romanesti!", "https://www.greuladeal.ro/content/images/2020/11/cabana-omu-apus.jpg"));
            newsList.add(new ArticleItemViewModel("Sambata de sus", "Cabana Valea Sambetei situata pe Valea Sambetei din Muntii Fagaras este un punct de plecare catre varful Moldoveanu cel mai inalt varf din Carpati.", "https://calatoriileioanei.ro/wp-content/uploads/2016/04/Cabana-Valea-S%C3%A2mbetei-cover.jpg"));
        }
    }
}
