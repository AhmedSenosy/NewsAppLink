package com.senosy.newsapp.Utils;


import com.senosy.newsapp.di.Component.DaggerApplicationComponent;

import dagger.android.AndroidInjector;
import dagger.android.support.DaggerApplication;


public class MyApplication extends DaggerApplication {
    @Override
    protected AndroidInjector<? extends DaggerApplication> applicationInjector() {
        return DaggerApplicationComponent.builder().Application(this).build();
    }


}
