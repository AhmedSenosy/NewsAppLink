package com.senosy.newsapp.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.google.gson.Gson;
import com.senosy.newsapp.Entity.Article;
import com.senosy.newsapp.Utils.Constants;
import com.senosy.newsapp.Utils.ViewModelProviderFactory;
import com.senosy.newsapp.databinding.FragmentHomeBinding;
import com.senosy.newsapp.ui.Detail.DetailNewsActivity;
import com.senosy.newsapp.ui.adapter.NewsAdapter;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import dagger.android.support.DaggerFragment;

public class HomeFragment extends DaggerFragment implements NewsAdapter.onClick {

    @Inject
    ViewModelProviderFactory providerFactory;
    NewsAdapter adapter;
    FragmentHomeBinding binding;
    private HomeViewModel homeViewModel;
    private List<Article> allArticles;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                ViewModelProviders.of(this, providerFactory).get(HomeViewModel.class);
        binding = FragmentHomeBinding.inflate(getLayoutInflater(), container, false);
        initViews();
        ObserveChanges();
        return binding.getRoot();
    }

    private void initViews() {
        allArticles = new ArrayList<>();
        adapter = new NewsAdapter(allArticles, this);
        binding.rvNews.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.rvNews.setAdapter(adapter);
    }

    private void ObserveChanges() {
        homeViewModel.getArticles().observe(getViewLifecycleOwner(),
                article -> {
                    if (article.getStatus().equals("ok"))
                        allArticles.addAll(article.getArticles());
                    adapter.notifyDataSetChanged();
                });
    }

    @Override
    public void setOnItemClicked(int position) {
        Intent intent = new Intent(getActivity(), DetailNewsActivity.class);
        Gson gson = new Gson();
        String passedObject = gson.toJson(allArticles.get(position));
        intent.putExtra(Constants.PASSED_OBJECT, passedObject);
        getActivity().startActivity(intent);
    }
}
