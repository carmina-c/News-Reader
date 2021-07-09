package com.carminacotiga.newsreader.ui.feature.navigator;

import android.os.Bundle;

import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.carminacotiga.newsreader.R;
import com.carminacotiga.newsreader.ui.feature.fragment.ArticleDetailsFragment;
import com.carminacotiga.newsreader.ui.feature.fragment.NewsListFragment;
import com.carminacotiga.newsreader.ui.feature.model.ArticleItemViewModel;

public class ArticleNavigator {
    private final FragmentManager fragmentManager;

    public ArticleNavigator(FragmentManager fragmentManager) {
        this.fragmentManager = fragmentManager;
    }

    public void openArticleScreen(ArticleItemViewModel item) {

        ArticleDetailsFragment fragment = new ArticleDetailsFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(ArticleDetailsFragment.EXTRA_ARTICLE_ITEM_ID, item.id);

        fragment.setArguments(bundle);

        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.container, fragment, ArticleDetailsFragment.class.getSimpleName());
        transaction.addToBackStack(fragment.getClass().getSimpleName());

        transaction.commit();
    }

    public void openListScreen() {

        NewsListFragment fragment = new NewsListFragment();

        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.container, fragment, NewsListFragment.class.getSimpleName());
        transaction.addToBackStack(fragment.getClass().getSimpleName());

        transaction.commit();

    }
}
