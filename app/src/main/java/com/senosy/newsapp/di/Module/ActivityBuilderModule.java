package com.senosy.newsapp.di.Module;

import com.senosy.newsapp.ui.Detail.DetailNewsActivity;
import com.senosy.newsapp.ui.NewsFeedActivity;
import com.senosy.newsapp.ui.home.HomeFragment;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class ActivityBuilderModule {

    @ContributesAndroidInjector
    abstract NewsFeedActivity contributeNewsFeedActivity();

    @ContributesAndroidInjector
    abstract HomeFragment contributeHomeFragment();

    @ContributesAndroidInjector
    abstract DetailNewsActivity contributeDetailActivity();


}
