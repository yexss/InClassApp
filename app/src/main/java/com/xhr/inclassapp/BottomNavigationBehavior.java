package com.xhr.inclassapp;


import android.view.View;

import androidx.annotation.NonNull;
import androidx.coordinatorlayout.widget.CoordinatorLayout;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class BottomNavigationBehavior extends CoordinatorLayout.Behavior<BottomNavigationView>  {
    @Override
    public boolean onStartNestedScroll(@NonNull CoordinatorLayout coordinatorLayout, @NonNull BottomNavigationView child, @NonNull View directTargetChild, @NonNull View target, int axes, int type) {
        return super.onStartNestedScroll(coordinatorLayout, child, directTargetChild, target, axes, type);
    }

    @Override
    public void onNestedPreScroll(@NonNull CoordinatorLayout coordinatorLayout, @NonNull BottomNavigationView child, @NonNull View target, int dx, int dy, @NonNull int[] consumed, int type) {
        super.onNestedPreScroll(coordinatorLayout, child, target, dx, dy, consumed, type);
    }
}
