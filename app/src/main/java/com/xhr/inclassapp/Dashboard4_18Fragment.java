package com.xhr.inclassapp;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import com.xhr.inclassapp.databinding.FragmentDashboard418Binding;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Dashboard4_18Fragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Dashboard4_18Fragment extends Fragment {

    private FragmentDashboard418Binding binding;

    public static Dashboard4_18Fragment newInstance() {
        Dashboard4_18Fragment fragment = new Dashboard4_18Fragment();
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding=FragmentDashboard418Binding.inflate(inflater,container,false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        DashboardPageAdapter adapter=new DashboardPageAdapter(this);
        binding.viewPager.setAdapter(adapter);
        binding.viewPager.setOffscreenPageLimit(adapter.getItemCount()-1);

        // 将tablayout与viewpager进行关联
        new TabLayoutMediator(binding.tabsDashboard, binding.viewPager, (tab, position) -> tab.setText("dash"+position)).attach();
    }

    private static class DashboardPageAdapter extends FragmentStateAdapter{

        public DashboardPageAdapter(@NonNull Fragment fragment) {
            super(fragment);
        }

        @NonNull
        @Override
        public Fragment createFragment(int position) {
            return DashboardChild4_18Fragment.newInstance(position);
        }

        @Override
        public int getItemCount() {
            return 2;
        }
    }
}