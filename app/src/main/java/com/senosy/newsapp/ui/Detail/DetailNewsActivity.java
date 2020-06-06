package com.senosy.newsapp.ui.Detail;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import androidx.annotation.NonNull;

import com.google.gson.Gson;
import com.senosy.newsapp.Entity.Article;
import com.senosy.newsapp.R;
import com.senosy.newsapp.Utils.Constants;
import com.senosy.newsapp.databinding.ActivityDetailNewsBinding;
import com.squareup.picasso.Picasso;

import javax.inject.Inject;

import dagger.android.support.DaggerAppCompatActivity;


public class DetailNewsActivity extends DaggerAppCompatActivity {

    ActivityDetailNewsBinding binding;
    Article article;
    @Inject
    Gson gson;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDetailNewsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setTitle("LINK DEVELOPMENT");
        getData();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.news_feed, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void getData() {
        Intent intent = getIntent();
        if (intent.hasExtra(Constants.PASSED_OBJECT)) {
            article = gson.fromJson(intent.getStringExtra(Constants.PASSED_OBJECT), Article.class);
            addDataToView();
        }
    }

    private void addDataToView() {
        binding.txtDetailTitle.setText(article.getTitle());
        binding.txtDetailWrittenBy.setText(article.getAuthor());
        binding.txtContent.setText(article.getDescription());
        binding.txtDateDetailPosts.setText(article.getPublishedAt());
        Picasso.get().load(article.getUrlToImage()).placeholder(R.drawable.placeholder)
                .into(binding.imgDetailBanner);

        binding.btnOpenWebsite.setOnClickListener(__ -> {
            if (article.getUrl() != null || !article.getUrl().isEmpty()) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(article.getUrl()));
                startActivity(intent);
            }
        });
    }
}
