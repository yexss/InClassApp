package com.xhr.inclassapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationBarView;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.snackbar.Snackbar;
import com.xhr.inclassapp.databinding.ActivityMetaDesign418Binding;

public class MetaDesign4_18Activity extends AppCompatActivity {

    private ActivityMetaDesign418Binding binding;
    private final NavigationBarView.OnItemSelectedListener navListener = new NavigationBarView.OnItemSelectedListener() {
        @SuppressLint("NonConstantResourceId")
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navgation_home:
//                    binding.tvMessage.setText(R.string.title_home);
                    binding.viewPager.setCurrentItem(0);
                    binding.toolbar.setTitle(R.string.title_home);
                    return true;
                case R.id.navgation_dashboard:
//                    binding.tvMessage.setText(R.string.title_dashboard);
                    binding.viewPager.setCurrentItem(1);
                    binding.toolbar.setTitle(R.string.title_dashboard);
                    return true;
                case R.id.navgation_notification:
//                    binding.tvMessage.setText(R.string.title_notification);
                    binding.viewPager.setCurrentItem(2);
                    binding.toolbar.setTitle(R.string.title_notification);
                    return true;
            }
            // 没处理
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMetaDesign418Binding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // 设置自定义Toolbar
        setSupportActionBar(binding.toolbar); // 界面主题设成NoActionBar

        // 配置viewPager适配器
        binding.navigation.setOnItemSelectedListener(navListener);
        final MainViewPageAdapter viewPageAdapter = new MainViewPageAdapter(this);
        binding.viewPager.setAdapter(viewPageAdapter);
        binding.viewPager.setOffscreenPageLimit(viewPageAdapter.getItemCount() - 1);
        // 滑动禁止
        binding.viewPager.setUserInputEnabled(false);

        // 注册viewPager的滑动事件监听
        binding.viewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                binding.navigation.getMenu().getItem(position).setChecked(true);
            }
        });

        binding.navigation.setOnItemSelectedListener(navListener);
        bindNavigation();

    }

    @SuppressLint("NonConstantResourceId")
    private void bindNavigation() {
        final ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this,
                binding.drawerLayout,
                binding.toolbar,
                R.string.navigation_drawer_open,
                R.string.navigation_drawer_close
        );
        binding.drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        binding.navView.setNavigationItemSelectedListener(item -> {
            final int id = item.getItemId();
            switch (id) {
                case R.id.nav_home:
                case R.id.nav_gallery:
                case R.id.nav_slideshow:
                    showSnackBarMsg("左侧导航菜单-" + item.getTitle() + " click");
            }
            binding.drawerLayout.closeDrawer(GravityCompat.START);
            return false;
        });
    }

    public void onFABClick(View view) {
        showSnackBarMsg("FAB button clicked");
    }


    private void showSnackBarMsg(String message) {
        Snackbar snackbar = Snackbar.make(binding.coordinatorLayout, message, Snackbar.LENGTH_LONG);
        snackbar.setAction("OK", view -> snackbar.dismiss());
        snackbar.show();
    }

    private static class MainViewPageAdapter extends FragmentStateAdapter {


        public MainViewPageAdapter(@NonNull FragmentActivity fragmentActivity) {
            super(fragmentActivity);
        }

        @NonNull
        @Override
        public Fragment createFragment(int position) {
            switch (position) {
                case 0:
                    return Home4_18Fragment.newInstance();
                case 1:
                    return Dashboard4_18Fragment.newInstance();
                case 2:
                    return Notification4_18Fragment.newInstance();
            }
            return Home4_18Fragment.newInstance();
        }

        @Override
        public int getItemCount() {
            return 3;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.meta_design_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId()==R.id.popup){
            showSnackBarMsg("pop option menu clicked");
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}