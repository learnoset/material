package com.learnoset.material.ui.learnosetnavigationbar;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.google.android.material.navigation.NavigationView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class LearnosetNavigationBar extends NavigationView {

    private final Context context;
    private boolean darkModeEnabled = false;
    private ImageView headerImage;
    private TextView profileName;

    public LearnosetNavigationBar(@NonNull Context context) {
        super(context);
        this.context = context;
        init();
    }

    public void enableDarkMode(boolean isDarkModeEnabled) {
        darkModeEnabled = isDarkModeEnabled;
    }

    @SuppressLint("InflateParams")
    private void init() {

        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View headerView = layoutInflater.inflate(R.layout.nav_header, null);
        addHeaderView(headerView);

        profileName = headerView.findViewById(R.id.headerProfileName);
        headerImage = headerView.findViewById(R.id.headerImageView);
        final TextView wishMessage = headerView.findViewById(R.id.headerWishMessage);
        wishMessage.setText(generateWishMessage());
    }

    private String generateWishMessage() {

        final int getCurrentHour = Integer.parseInt(new SimpleDateFormat("HH", Locale.getDefault()).format(new Date()));

        if (getCurrentHour < 12) {
            return "Good Morning";
        } else if (getCurrentHour > 12 && getCurrentHour < 18) {
            return "Good Afternoon";
        } else {
            return "Good Evening";
        }

    }
}
