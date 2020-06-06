package com.senosy.newsapp.ui.home;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.google.gson.Gson;
import com.senosy.newsapp.Entity.Article;
import com.senosy.newsapp.Entity.BaseEntity;
import com.senosy.newsapp.Utils.ApiInterface;
import com.senosy.newsapp.Utils.Constants;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.BiFunction;
import io.reactivex.schedulers.Schedulers;

public class HomeViewModel extends ViewModel {

    @Inject
    ApiInterface apiInterface;
    private MutableLiveData<BaseEntity<List<Article>>> articles;
    private Gson gson;
    private CompositeDisposable disposable;

    @Inject
    public HomeViewModel(Gson gson) {
        articles = new MutableLiveData<>();
        this.gson = gson;
        disposable = new CompositeDisposable();
    }

    @Inject
    public void getArticle() {


        Observable.zip(getNextWebNews().toObservable(), getAssociatedPressNews().toObservable(), new BiFunction<BaseEntity<List<Article>>, BaseEntity<List<Article>>, BaseEntity<List<Article>>>() {
            @Override
            public BaseEntity<List<Article>> apply(BaseEntity<List<Article>> listBaseEntity, BaseEntity<List<Article>> listBaseEntity2) throws Exception {
                List<Article> resultcom = new ArrayList<>();
                resultcom.addAll(listBaseEntity.getArticles());
                resultcom.addAll(listBaseEntity2.getArticles());
                return new BaseEntity<>(listBaseEntity.getStatus(),
                        listBaseEntity.getSource() + listBaseEntity2.getSource(), listBaseEntity.getSortBy(),
                        resultcom);
            }
        }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        result -> {
                            articles.setValue(result);
                            articles.postValue(result);
                        },
                        throwable -> {
                            articles.setValue(new BaseEntity<>("Error", "", "", null));
                        }
                );
    }

    private Single<BaseEntity<List<Article>>> getNextWebNews() {
        return apiInterface.getArticle(Constants.NEXT_WEB, Constants.API_KEY);
    }

    private Single<BaseEntity<List<Article>>> getAssociatedPressNews() {
        return apiInterface.getArticle(Constants.ASSOCIATED_PRESS, Constants.API_KEY);
    }

    public MutableLiveData<BaseEntity<List<Article>>> getArticles() {
        return articles;
    }
}