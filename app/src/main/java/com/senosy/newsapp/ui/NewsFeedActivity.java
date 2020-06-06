package com.senosy.newsapp.ui;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.blankj.utilcode.util.ToastUtils;
import com.google.android.material.navigation.NavigationView;
import com.senosy.newsapp.R;
import com.senosy.newsapp.databinding.ActivityNewsFeedBinding;

import dagger.android.support.DaggerAppCompatActivity;

public class NewsFeedActivity extends DaggerAppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    ActivityNewsFeedBinding binding;
    private AppBarConfiguration mAppBarConfiguration;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityNewsFeedBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = binding.drawerLayout;
        NavigationView navigationView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_explore, R.id.nav_gallery)
                .setDrawerLayout(drawer)
                .build();
        navigationView.setNavigationItemSelectedListener(this);
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.news_feed, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

        int id = menuItem.getItemId();
        switch (id) {
            case R.id.nav_explore:
                ToastUtils.showShort(getText(R.string.menu_Explore));
                break;
            case R.id.nav_gallery:
                ToastUtils.showShort(getText(R.string.menu_gallery));
                break;
            case R.id.nav_live_chat:
                ToastUtils.showShort(getText(R.string.menu_live_chat));
                break;
            case R.id.nav_wish:
                ToastUtils.showShort(getText(R.string.menu_wish));
                break;
            case R.id.nav_magazine:
                ToastUtils.showShort(getText(R.string.menu_magazine));
                break;
        }


        DrawerLayout drawer = binding.drawerLayout;
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }


}
