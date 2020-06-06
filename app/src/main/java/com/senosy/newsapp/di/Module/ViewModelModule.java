package com.senosy.newsapp.di.Module;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.senosy.newsapp.Utils.ViewModelProviderFactory;
import com.senosy.newsapp.di.ViewModelKey;
import com.senosy.newsapp.ui.home.HomeViewModel;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;

@Module
public abstract class ViewModelModule {

    @Binds
    public abstract ViewModelProvider.Factory bindViewModelFactory(ViewModelProviderFactory viewModelFactory);

    @Binds
    @IntoMap
    @ViewModelKey(HomeViewModel.class)
    public abstract ViewModel bindHomeViewModel(HomeViewModel homeViewModel);


}
