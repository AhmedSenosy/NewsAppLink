package com.senosy.newsapp.di.Component;


import com.senosy.newsapp.Utils.MyApplication;
import com.senosy.newsapp.di.Module.ActivityBuilderModule;
import com.senosy.newsapp.di.Module.RetrofitModule;
import com.senosy.newsapp.di.Module.ViewModelModule;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjector;
import dagger.android.support.AndroidSupportInjectionModule;

@Singleton
@Component(modules = {
        AndroidSupportInjectionModule.class,
        ActivityBuilderModule.class,
        RetrofitModule.class,
        ViewModelModule.class})
public interface ApplicationComponent extends AndroidInjector<MyApplication> {


    @Component.Builder
    interface Builder {
        @BindsInstance
        Builder Application(MyApplication application);

        ApplicationComponent build();
    }
}
