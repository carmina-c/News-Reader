package com.carminacotiga.newsreader;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.carminacotiga.newsreader.ui.feature.model.NewsListViewModel;
import com.carminacotiga.newsreader.ui.feature.model.factory.ViewModelFactory;
import com.carminacotiga.newsreader.ui.feature.navigator.ArticleNavigator;

public class NewsReaderActivity extends AppCompatActivity {

    private ArticleNavigator navigator;

    @Override
    public void onBackPressed() {
        navigator.openListScreen();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.news_reader_activity);

        navigator = new ArticleNavigator(getSupportFragmentManager());

        ViewModelFactory factory = new ViewModelFactory(getApplication());

        NewsListViewModel viewModel = new ViewModelProvider(this, factory).get(NewsListViewModel.class);

        viewModel.openArticle.observe(this, articleItemViewModel -> navigator.openArticleScreen(articleItemViewModel));

    }
}