package com.xhr.inclassapp;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.coordinatorlayout.widget.CoordinatorLayout;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

public class BottomFAB4_18Behavior extends CoordinatorLayout.Behavior<FloatingActionButton> {

    // 在xml当中加载自定义的Behavior
    public BottomFAB4_18Behavior(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean layoutDependsOn(@NonNull CoordinatorLayout parent, @NonNull FloatingActionButton child, @NonNull View dependency) {
        return dependency instanceof Snackbar.SnackbarLayout;
    }

    @Override
    public boolean onDependentViewChanged(@NonNull CoordinatorLayout parent, @NonNull FloatingActionButton child, @NonNull View dependency) {
        return this.updateButton(child, dependency);
    }

    private boolean updateButton(FloatingActionButton child, View dependency) {
        if (dependency instanceof Snackbar.SnackbarLayout) {
            final float oldY = child.getTranslationY();
            final float height = dependency.getHeight();
            final float newY = dependency.getTranslationY() - height;
            child.setTranslationY(newY);
            return oldY != newY;
        }
        return false;
    }

    @Override
    public void onDependentViewRemoved(@NonNull CoordinatorLayout parent, @NonNull FloatingActionButton child, @NonNull View dependency) {
        child.setTranslationY(0.0f);
    }
}
