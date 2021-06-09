package com.carminacotiga.newsreader.ui.feature.fragment;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.carminacotiga.newsreader.databinding.NewsListFragmentBinding;
import com.carminacotiga.newsreader.ui.feature.model.NewsListViewModel;
import com.carminacotiga.newsreader.ui.feature.model.factory.ViewModelFactory;
import com.carminacotiga.newsreader.ui.feature.navigator.AlertNavigator;

public class MainFragment extends Fragment {

    private NewsListViewModel viewModel;
    private AlertNavigator alertNavigator;

    public static MainFragment newInstance() {
        return new MainFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        alertNavigator = new AlertNavigator(getChildFragmentManager(), requireContext());

        viewModel = new ViewModelProvider(this, new ViewModelFactory(requireActivity().getApplication())).get(NewsListViewModel.class);

        viewModel.error.observe(this, throwable -> alertNavigator.showErrorFor(throwable));
        viewModel.openLink.observe(this, this::openLink);

        getLifecycle().addObserver(viewModel);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        NewsListFragmentBinding binding = NewsListFragmentBinding.inflate(inflater, container, false);

        binding.setViewModel(viewModel);

        return binding.getRoot();
    }

    private void openLink(@NonNull String link) {
        Intent i = new Intent(Intent.ACTION_VIEW);
        i.setData(Uri.parse(link));
        startActivity(i);
    }

}