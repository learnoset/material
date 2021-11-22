package com.learnoset.material.ui.learnosetnavigationbar;

import android.annotation.SuppressLint;
import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.FileProvider;

import com.google.android.material.navigation.NavigationView;
import com.squareup.picasso.Picasso;

import java.io.File;
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

    public void initializeHeader(String profileImageUrl, String profileNameTxt) {
        Picasso.get().load(profileImageUrl).into(headerImage);
        profileName.setText(profileNameTxt);
    }

    public void initializeHeader(File profileImageFile, String profileNameTxt) throws LearnosetExceptions {
        profileName.setText(profileNameTxt);

        if (profileImageFile.exists()) {
            Uri imageUri = FileProvider.getUriForFile(
                    context,
                    "com.learnoset.fileprovider",
                    profileImageFile);
            headerImage.setImageURI(imageUri);
        } else {
            throw new LearnosetExceptions("Invalid File Path");
        }
    }

    public void initializeHeader(Uri profileImageUri, String profileNameTxt) throws LearnosetExceptions {
        profileName.setText(profileNameTxt);

        final ContentResolver contentResolver = context.getContentResolver();
        final String[] projection = {MediaStore.MediaColumns.DATA};

        final Cursor cursor = contentResolver.query(profileImageUri, projection, null, null, null);
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                String filePath = cursor.getString(0);

                if (new File(filePath).exists()) {
                    headerImage.setImageURI(profileImageUri);
                } else {
                    throw new LearnosetExceptions("No File Found");
                }
            } else {
                throw new LearnosetExceptions("Invalid URI Format");
            }
            cursor.close();
        } else {
            throw new LearnosetExceptions("Invalid URI");
        }

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
