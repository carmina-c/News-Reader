package com.carminacotiga.newsreader.view.bindings;

import androidx.databinding.BindingAdapter;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.carminacotiga.newsreader.ui.feature.adapter.ArticleAdapter;
import com.carminacotiga.newsreader.ui.feature.listener.ArticleHandler;
import com.carminacotiga.newsreader.ui.feature.model.ArticleItemViewModel;

import java.util.List;

public class RecyclerBindings {

    @BindingAdapter({"newsList", "articleHandler"})
    public static void refresh(RecyclerView recyclerView, List<ArticleItemViewModel> newsList, ArticleHandler articleHandler) {
        ArticleAdapter adapter = (ArticleAdapter) recyclerView.getAdapter();

        if (adapter == null) {
            adapter = new ArticleAdapter(articleHandler);
            recyclerView.setLayoutManager(new LinearLayoutManager(recyclerView.getContext()));
            recyclerView.setAdapter(adapter);
        }

        adapter.setItems(newsList, articleHandler);
    }
}
