package com.senosy.newsapp.Utils;

import com.senosy.newsapp.Entity.Article;
import com.senosy.newsapp.Entity.BaseEntity;

import java.util.List;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiInterface {

    @GET(Constants.BASE_URL)
    Single<BaseEntity<List<Article>>> getArticle(@Query("source") String source,
                                                 @Query("apiKey") String apiKey);
}
