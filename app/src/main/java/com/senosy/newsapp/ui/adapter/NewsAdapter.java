package com.senosy.newsapp.ui.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.senosy.newsapp.Entity.Article;
import com.senosy.newsapp.R;
import com.senosy.newsapp.databinding.NewsItemBinding;
import com.squareup.picasso.Picasso;

import java.util.List;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.viewHolder> {

    NewsItemBinding binding;
    List<Article> articles;
    onClick onClick;

    public NewsAdapter(List<Article> articles, NewsAdapter.onClick onClick) {
        this.articles = articles;
        this.onClick = onClick;
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        binding = NewsItemBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new viewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {
        Article article = articles.get(holder.getAdapterPosition());
        binding.txtTitle.setText(article.getTitle());
        binding.txtWriterName.setText(article.getAuthor());
        binding.txtDatePosted.setText(article.getPublishedAt());
        if (!article.getUrlToImage().isEmpty()) {
            Picasso.get().load(article.getUrlToImage())
                    .placeholder(R.drawable.placeholder).into(binding.imgBanner);
        } else {
            binding.imgBanner.setImageResource(R.drawable.placeholder);
        }

    }

    @Override
    public int getItemCount() {
        return articles.size();
    }

    public interface onClick {
        void setOnItemClicked(int position);
    }

    class viewHolder extends RecyclerView.ViewHolder {
        TextView tv_title, tv_writer_name, tv_date;
        ImageView img_banner;

        public viewHolder(@NonNull NewsItemBinding binding) {
            super(binding.getRoot());
            tv_date = binding.txtDatePosted;
            tv_writer_name = binding.txtWriterName;
            tv_title = binding.txtTitle;
            img_banner = binding.imgBanner;

            binding.getRoot().setOnClickListener(__ -> {
                onClick.setOnItemClicked(getAdapterPosition());
            });
        }
    }
}
