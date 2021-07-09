package com.carminacotiga.newsreader.ui.feature.fragment;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.carminacotiga.newsreader.R;
import com.carminacotiga.newsreader.databinding.NewsListFragmentBinding;
import com.carminacotiga.newsreader.ui.feature.model.NewsListViewModel;
import com.carminacotiga.newsreader.ui.feature.model.factory.ViewModelFactory;
import com.carminacotiga.newsreader.ui.feature.navigator.AlertNavigator;

import org.jetbrains.annotations.NotNull;

public class NewsListFragment extends Fragment {

    private NewsListViewModel viewModel;
    private AlertNavigator alertNavigator;

    public static NewsListFragment newInstance() {
        return new NewsListFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        alertNavigator = new AlertNavigator(getChildFragmentManager(), requireContext());

        viewModel = new ViewModelProvider(requireActivity(), new ViewModelFactory(requireActivity().getApplication())).get(NewsListViewModel.class);

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

    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        SwipeRefreshLayout swipeRefreshLayout = view.findViewById(R.id.refreshLayout);
        swipeRefreshLayout.setOnRefreshListener(
                () -> {
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            viewModel.refresh();
                            swipeRefreshLayout.setRefreshing(false);
                        }
                    }, 3000);
                }
        );
    }

    private void openLink(@NonNull String link) {
        Intent i = new Intent(Intent.ACTION_VIEW);
        i.setData(Uri.parse(link));
        startActivity(i);
    }

}