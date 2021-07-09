package com.carminacotiga.newsreader.ui.feature.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.carminacotiga.newsreader.databinding.ArticleDetailsFragmentBinding;
import com.carminacotiga.newsreader.ui.feature.model.ArticleDetailsViewModel;
import com.carminacotiga.newsreader.ui.feature.model.factory.ViewModelFactory;

public class ArticleDetailsFragment extends Fragment {

    public static String EXTRA_ARTICLE_ITEM_ID = "EXTRA_ARTICLE_ITEM_ID";
    private ArticleDetailsViewModel viewModel;

    @Override
    public void onCreate(@Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ViewModelFactory factory = new ViewModelFactory(getActivity().getApplication());

        viewModel = new ViewModelProvider(this, factory).get(ArticleDetailsViewModel.class);

        if (getArguments() != null && getArguments().containsKey(EXTRA_ARTICLE_ITEM_ID)) {
            viewModel.initArticle(getArguments().getInt(EXTRA_ARTICLE_ITEM_ID));
        }

        getLifecycle().addObserver(viewModel);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        ArticleDetailsFragmentBinding binding = ArticleDetailsFragmentBinding.inflate(inflater, container, false);

        binding.setViewModel(viewModel);

        return binding.getRoot();
    }
}

