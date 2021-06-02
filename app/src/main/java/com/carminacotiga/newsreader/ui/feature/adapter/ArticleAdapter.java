package com.carminacotiga.newsreader.ui.feature.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.carminacotiga.newsreader.databinding.NewsItemBinding;
import com.carminacotiga.newsreader.ui.feature.listener.ArticleHandler;
import com.carminacotiga.newsreader.ui.feature.model.ArticleItemViewModel;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class ArticleAdapter extends RecyclerView.Adapter<ArticleAdapter.ItemViewHolder> {

    private List<ArticleItemViewModel> articleModeList;
    private ArticleHandler handler;

    public ArticleAdapter(ArticleHandler handler) {
        this.articleModeList = new ArrayList<>();
        this.handler = handler;
    }

    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        NewsItemBinding binder = NewsItemBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new ItemViewHolder(binder);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull ItemViewHolder holder, int position) {
        holder.binding.setViewModel(articleModeList.get(position));
        //holder.binding.setHandler(handler);
    }

    @Override
    public int getItemCount() {
        return articleModeList.size();
    }

    public void setItems(List<ArticleItemViewModel> items, ArticleHandler handler) {
        this.handler = handler;
        this.articleModeList = items;
        notifyDataSetChanged();
    }

    public class ItemViewHolder extends RecyclerView.ViewHolder {
        final NewsItemBinding binding;

        public ItemViewHolder(NewsItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
