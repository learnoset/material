package com.learnoset.material.ui.learnosetnavigationbar;

import android.annotation.SuppressLint;
import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.FileProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.navigation.NavigationView;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class LearnosetNavigationBar extends NavigationView {

    public static int GROUPS_COUNT = 0;
    private final Context context;
    private final List<LearnosetNavItem> learnosetNavItems = new ArrayList<>();
    private final List<NavItemsGroup> navItemsGroups = new ArrayList<>();
    private ImageView headerImage;
    private TextView profileName;
    private NavigationAdapter navigationAdapter;
    private NavThemes selectedTheme = NavThemes.LIGHT;
    private final IconColors selectedIconColor = IconColors.DEFAULT;

    public LearnosetNavigationBar(@NonNull Context context) {
        super(context);
        this.context = context;
        init();
    }

    public LearnosetNavigationBar(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        init();
    }

    public void setTheme(NavThemes theme) {
        this.selectedTheme = theme;
    }

    public void setHeaderData(String profileNameTxt) {
        profileName.setText(profileNameTxt);
    }

    public void setHeaderData(@Nullable String profileImageUrl, String profileNameTxt) {

        if (profileImageUrl != null) {
            Picasso.get().load(profileImageUrl).into(headerImage);
        }

        profileName.setText(profileNameTxt);
    }

    public void setHeaderData(File profileImageFile, String profileNameTxt) throws LearnosetExceptions {
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

    public void setHeaderData(Uri profileImageUri, String profileNameTxt) throws LearnosetExceptions {
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

    public void addNavItem(LearnosetNavItem.BuiltInItems builtInItems) {
        LearnosetNavItem learnosetNavItem = new LearnosetNavItem();

        if (builtInItems == LearnosetNavItem.BuiltInItems.DASHBOARD) {
            learnosetNavItem.setTitle("Dashboard");
        } else if (builtInItems == LearnosetNavItem.BuiltInItems.HOME) {
            learnosetNavItem.setTitle("Home");
        }
        learnosetNavItems.add(learnosetNavItem);
    }

    public void addNavItem(LearnosetNavItem learnosetNavItem) {
        learnosetNavItems.add(learnosetNavItem);
        navigationAdapter.notifyDataSetChanged();
    }

    public void addNavItems(List<LearnosetNavItem> learnosetNavItem) {
        learnosetNavItems.addAll(learnosetNavItem);
        navigationAdapter.notifyDataSetChanged();
    }

    public void addItemsGroup(NavItemsGroup navItemsGroup) {
        navItemsGroups.add(navItemsGroup);
        learnosetNavItems.addAll(navItemsGroup.getGroupItems());
        navigationAdapter.notifyDataSetChanged();
    }

    public void removeItem(int itemPosition) {
        learnosetNavItems.remove(itemPosition);
        navigationAdapter.notifyDataSetChanged();
    }

    public void removeGroup(String groupName) {

        int getGroupId = 0;

        for (int i = 0; i < navItemsGroups.size(); i++) {
            if (navItemsGroups.get(i).getGroupName().equals(groupName)) {
                getGroupId = navItemsGroups.get(i).id;
                break;
            }
        }

        for (int l = 0; l < learnosetNavItems.size(); l++) {
            if (learnosetNavItems.get(l).groupId == getGroupId) {
                learnosetNavItems.remove(l);
                l--;
            }
        }


        navigationAdapter.notifyDataSetChanged();
    }

    @SuppressLint("InflateParams")
    private void init() {

        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View headerView = layoutInflater.inflate(R.layout.nav_header, null);
        addHeaderView(headerView);

        View bodyView = layoutInflater.inflate(R.layout.nav_layout, null);
        addView(bodyView);

        profileName = headerView.findViewById(R.id.headerProfileName);
        headerImage = headerView.findViewById(R.id.headerImageView);

        final TextView wishMessage = headerView.findViewById(R.id.headerWishMessage);
        wishMessage.setText(generateWishMessage());

        final RecyclerView navItemsRecyclerView = bodyView.findViewById(R.id.navItemsRecyclerView);
        navItemsRecyclerView.setHasFixedSize(true);
        navItemsRecyclerView.setLayoutManager(new LinearLayoutManager(context));

        navigationAdapter = new NavigationAdapter(context, learnosetNavItems, navItemsGroups, selectedIconColor, selectedTheme);

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

    public enum IconsShape {
        OUTLINE,
        FILL
    }

    public enum IconColors {
        DEFAULT,
        GRAY,
        RED,
        DARK_RED,
        LIGHT_RED,
        BLACK,
        WHITE,
        ORANGE,
        DARK_ORANGE,
        LIGHT_ORANGE,
        YELLOW,
        BLUE,
        LIGHT_BLUE,
        DARK_BLUE
    }

    public enum NavThemes {
        LIGHT,
        DARK
    }
}
