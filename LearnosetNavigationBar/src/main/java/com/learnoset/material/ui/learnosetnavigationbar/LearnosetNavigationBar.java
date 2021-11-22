package com.learnoset.material.ui.learnosetnavigationbar;

import android.content.Context;

import androidx.annotation.NonNull;

import com.google.android.material.navigation.NavigationView;

public class LearnosetNavigationBar extends NavigationView {

    private static boolean darkModeEnabled = false;

    public LearnosetNavigationBar(@NonNull Context context) {
        super(context);
    }

    public static void enableDarkMode(boolean isDarkModeEnabled){
        darkModeEnabled = isDarkModeEnabled;
    }


}
