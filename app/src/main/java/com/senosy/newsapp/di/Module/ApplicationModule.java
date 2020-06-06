package com.senosy.newsapp.di.Module;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.senosy.newsapp.Utils.MyApplication;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class ApplicationModule {
    protected final MyApplication application;

    public ApplicationModule(MyApplication application) {
        this.application = getApplication();
    }

    @Provides
    @Singleton
    public MyApplication getApplication() {
        return application;
    }

    @Provides
    @Singleton
    public Context provideContext(Application application) {
        return application;
    }

    @Provides
    @Singleton
    public SharedPreferences providesSharedPreferences(Application application) {
        return PreferenceManager.getDefaultSharedPreferences(application);
    }


}
