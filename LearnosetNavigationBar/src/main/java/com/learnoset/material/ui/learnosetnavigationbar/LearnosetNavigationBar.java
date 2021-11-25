package com.learnoset.material.ui.learnosetnavigationbar;

import android.annotation.SuppressLint;
import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.Uri;
import android.provider.MediaStore;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.FileProvider;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
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
    public static int selectedItemPosition = 0;
    public static int drawerGravity = GravityCompat.START;

    private final Context context;
    private final List<LearnosetNavItem> learnosetNavItems = new ArrayList<>();
    private final List<NavItemsGroup> navItemsGroups = new ArrayList<>();
    private int iconsColor;
    private int navItemTxtColor;
    private int navGroupTxtColor;
    private int selectedItemBackgroundColor;
    private int selectedItemTextColor;
    private int selectedItemIconColor;
    private ImageView headerImage;
    private TextView profileName;
    private NavigationAdapter navigationAdapter;
    private NavThemes selectedTheme = NavThemes.LIGHT;
    private LinearLayout headerRootLayout;
    private RelativeLayout bodyRootLayout;
    private RelativeLayout navLogOutLayout;
    private TextView navLogOutTxt;
    private TextView wishMessage;
    private NavigationEventListener navigationEventListener;

    public LearnosetNavigationBar(@NonNull Context context) {
        super(context);
        this.context = context;
        LearnosetNavigationBar.drawerGravity = GravityCompat.START;
        gettingSelectedThemeDetails();
        init();
    }

    public LearnosetNavigationBar(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        LearnosetNavigationBar.drawerGravity = GravityCompat.START;
        gettingSelectedThemeDetails();
        init();
    }

    public void setSelected(int navItemPosition) {

        learnosetNavItems.get(LearnosetNavigationBar.selectedItemPosition).setSelected(false);
        learnosetNavItems.get(navItemPosition).setSelected(true);

        navigationAdapter.reloadNavigationBar(iconsColor, selectedTheme, selectedItemBackgroundColor, navItemTxtColor, navGroupTxtColor);
    }

    public void setDrawerLayout(DrawerLayout drawerLayout, DrawerGravity drawerGravity) {

        if (drawerGravity == DrawerGravity.LEFT) {
            LearnosetNavigationBar.drawerGravity = GravityCompat.START;
        } else {
            LearnosetNavigationBar.drawerGravity = GravityCompat.END;
        }

        navigationAdapter.setDrawerLayout(drawerLayout);
    }

    public void setTheme(NavThemes theme) {
        this.selectedTheme = theme;

        if (selectedTheme == NavThemes.DARK) {
            headerRootLayout.setBackgroundColor(Color.parseColor("#0E0E0E"));
            bodyRootLayout.setBackgroundColor(Color.parseColor("#0E0E0E"));

            profileName.setTextColor(Color.parseColor("#FFFFFF"));
            wishMessage.setTextColor(Color.parseColor("#CCFFFFFF"));

            navLogOutTxt.setTextColor(Color.WHITE);
        }

        gettingSelectedThemeDetails();
        navigationAdapter.reloadNavigationBar(iconsColor, selectedTheme, selectedItemBackgroundColor, navItemTxtColor, navGroupTxtColor);
    }

    public void setTheme(CustomNavTheme customNavigationTheme) {

        if (customNavigationTheme.getNavigationBackground() != 0) {
            headerRootLayout.setBackgroundColor(customNavigationTheme.getNavigationBackground());
            bodyRootLayout.setBackgroundColor(customNavigationTheme.getNavigationBackground());
        }

        if (customNavigationTheme.getTextColor() != 0) {
            profileName.setTextColor(customNavigationTheme.getTextColor());
            wishMessage.setTextColor(customNavigationTheme.getTextColor());
            navLogOutTxt.setTextColor(customNavigationTheme.getTextColor());
            navItemTxtColor = customNavigationTheme.getTextColor();
        }

        if (customNavigationTheme.getIconsColor() != 0) {
            iconsColor = customNavigationTheme.getIconsColor();
        }

        if(customNavigationTheme.getGroupNameColor() != 0){
            navGroupTxtColor = customNavigationTheme.getGroupNameColor();
        }

        if (customNavigationTheme.getSelectedItemBackgroundColor() != 0) {
            selectedItemBackgroundColor = customNavigationTheme.getSelectedItemBackgroundColor();
        }

        if(customNavigationTheme.getSelectedItemIconColor() != 0){
            selectedItemIconColor = customNavigationTheme.getSelectedItemIconColor();
        }

        if(customNavigationTheme.getSelectedItemTextColor() != 0){
            selectedItemTextColor = customNavigationTheme.getSelectedItemTextColor();
        }

        navigationAdapter.reloadNavigationBar(iconsColor, selectedTheme, selectedItemBackgroundColor, navItemTxtColor, navGroupTxtColor);
    }

    private void gettingSelectedThemeDetails() {

        if (selectedTheme == NavThemes.DARK) {
            iconsColor = Color.parseColor("#E6FFFFFF");
            navItemTxtColor = Color.parseColor("#E6FFFFFF");
            navGroupTxtColor = Color.parseColor("#66FFFFFF");
            selectedItemBackgroundColor = Color.parseColor("#4C74FA");
            selectedItemIconColor = Color.WHITE;
            selectedItemTextColor = Color.WHITE;
        } else {
            iconsColor = Color.parseColor("#99000000");
            navItemTxtColor = Color.parseColor("#99000000");
            selectedItemBackgroundColor = Color.parseColor("#4C74FA");
            navGroupTxtColor = Color.parseColor("#66000000");
            selectedItemIconColor = Color.WHITE;
            selectedItemTextColor = Color.WHITE;
        }
    }

    private String getNavColorValue(NavColors navColor) {

        String selectedColorValue = "";

        if (navColor == LearnosetNavigationBar.NavColors.RED) {
            selectedColorValue = "#FFFF1744";
        } else if (navColor == LearnosetNavigationBar.NavColors.BLACK) {
            selectedColorValue = "#000000";
        } else if (navColor == LearnosetNavigationBar.NavColors.GRAY) {
            selectedColorValue = "#998A8A8A";
        } else if (navColor == LearnosetNavigationBar.NavColors.ORANGE) {
            selectedColorValue = "#FF9100";
        } else if (navColor == LearnosetNavigationBar.NavColors.WHITE) {
            selectedColorValue = "#FFFFFF";
        } else if (navColor == LearnosetNavigationBar.NavColors.YELLOW) {
            selectedColorValue = "#FFEA00";
        } else if (navColor == LearnosetNavigationBar.NavColors.DARK_RED) {
            selectedColorValue = "#FFD50000";
        } else if (navColor == LearnosetNavigationBar.NavColors.LIGHT_RED) {
            selectedColorValue = "#FFFF8A80";
        } else if (navColor == LearnosetNavigationBar.NavColors.DARK_ORANGE) {
            selectedColorValue = "#FFFF6D00";
        } else if (navColor == LearnosetNavigationBar.NavColors.LIGHT_ORANGE) {
            selectedColorValue = "#FFFFD180";
        } else if (navColor == LearnosetNavigationBar.NavColors.BLUE) {
            selectedColorValue = "#FF00B0FF";
        } else if (navColor == LearnosetNavigationBar.NavColors.DARK_BLUE) {
            selectedColorValue = "#FF0091EA";
        } else if (navColor == LearnosetNavigationBar.NavColors.LIGHT_BLUE) {
            selectedColorValue = "#FF80D8FF";
        }

        return selectedColorValue;
    }


    public void setEventListener(NavigationEventListener navigationEventListener) {
        this.navigationEventListener = navigationEventListener;
    }

    public void setIconsColor(NavColors iconsColor) {

        if (iconsColor == NavColors.DEFAULT) {
            gettingSelectedThemeDetails();
        } else {
            this.iconsColor = Color.parseColor(getNavColorValue(iconsColor));
        }

        navigationAdapter.reloadNavigationBar(this.iconsColor, selectedTheme, selectedItemBackgroundColor, navItemTxtColor, navGroupTxtColor);
    }

    public void setSelectedItemBackground(NavColors selectedItemBackgroundColor) {
        if (selectedItemBackgroundColor == NavColors.DEFAULT) {
            gettingSelectedThemeDetails();
        } else {
            this.selectedItemBackgroundColor = Color.parseColor(getNavColorValue(selectedItemBackgroundColor));
        }

        navigationAdapter.reloadNavigationBar(iconsColor, selectedTheme, this.selectedItemBackgroundColor, navItemTxtColor, navGroupTxtColor);
    }

    public void setHeaderData(String profileNameTxt) {
        profileName.setText(profileNameTxt);
    }

    public void setHeaderData(String profileNameTxt, @Nullable String profileImageUrl) {

        if (profileImageUrl != null) {
            Picasso.get().load(profileImageUrl).into(headerImage);
        }

        profileName.setText(profileNameTxt);
    }

    public void setHeaderData(String profileNameTxt, @Nullable int profileImageResId) {
        headerImage.setImageResource(profileImageResId);
        profileName.setText(profileNameTxt);
    }

    public void setHeaderData(String profileNameTxt, @Nullable File profileImageFile) throws LearnosetExceptions {
        profileName.setText(profileNameTxt);

        if (profileImageFile.exists()) {
            headerImage.setImageBitmap(BitmapFactory.decodeFile(profileImageFile.getAbsolutePath()));
        } else {
            throw new LearnosetExceptions("Invalid File Path");
        }
    }

    public void setHeaderData(String profileNameTxt, @Nullable Uri profileImageUri) throws LearnosetExceptions {
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
            learnosetNavItem.setIcon(R.drawable.dashboard_icon);
        } else if (builtInItems == LearnosetNavItem.BuiltInItems.HOME) {
            learnosetNavItem.setTitle("Home");
            learnosetNavItem.setIcon(R.drawable.home_icon);
        } else if (builtInItems == LearnosetNavItem.BuiltInItems.SEND) {
            learnosetNavItem.setTitle("Send");
            learnosetNavItem.setIcon(R.drawable.send_icon);
        } else if (builtInItems == LearnosetNavItem.BuiltInItems.SETTINGS) {
            learnosetNavItem.setTitle("Settings");
            learnosetNavItem.setIcon(R.drawable.settings_icon);
        } else if (builtInItems == LearnosetNavItem.BuiltInItems.ABOUT_US) {
            learnosetNavItem.setTitle("About Us");
            learnosetNavItem.setIcon(R.drawable.about_us_icon);
        } else if (builtInItems == LearnosetNavItem.BuiltInItems.CONTACT_US) {
            learnosetNavItem.setTitle("Contact Us");
            learnosetNavItem.setIcon(R.drawable.contact_us_icon);
        } else if (builtInItems == LearnosetNavItem.BuiltInItems.DOWNLOAD) {
            learnosetNavItem.setTitle("Downloads");
            learnosetNavItem.setIcon(R.drawable.download_icon);
        } else if (builtInItems == LearnosetNavItem.BuiltInItems.EMAIL) {
            learnosetNavItem.setTitle("Email");
            learnosetNavItem.setIcon(R.drawable.email_icon);
        } else if (builtInItems == LearnosetNavItem.BuiltInItems.FAVOURITES) {
            learnosetNavItem.setTitle("Favourites");
            learnosetNavItem.setIcon(R.drawable.favourite_icon);
        } else if (builtInItems == LearnosetNavItem.BuiltInItems.GALLERY) {
            learnosetNavItem.setTitle("Gallery");
            learnosetNavItem.setIcon(R.drawable.gallery_icon);
        } else if (builtInItems == LearnosetNavItem.BuiltInItems.HELP) {
            learnosetNavItem.setTitle("Help");
            learnosetNavItem.setIcon(R.drawable.help_icon);
        } else if (builtInItems == LearnosetNavItem.BuiltInItems.MESSAGE) {
            learnosetNavItem.setTitle("Message");
            learnosetNavItem.setIcon(R.drawable.message_icon);
        } else if (builtInItems == LearnosetNavItem.BuiltInItems.FEEDBACK) {
            learnosetNavItem.setTitle("Feedback");
            learnosetNavItem.setIcon(R.drawable.feedback_icon);
        } else if (builtInItems == LearnosetNavItem.BuiltInItems.PRIVACY_POLICY) {
            learnosetNavItem.setTitle("Privacy Policy");
            learnosetNavItem.setIcon(R.drawable.privacy_policy_icon);
        } else if (builtInItems == LearnosetNavItem.BuiltInItems.RATE_US) {
            learnosetNavItem.setTitle("Rate Us");
            learnosetNavItem.setIcon(R.drawable.rate_us_icon);
        } else if (builtInItems == LearnosetNavItem.BuiltInItems.UPLOAD) {
            learnosetNavItem.setTitle("Upload");
            learnosetNavItem.setIcon(R.drawable.upload_icon);
        } else if (builtInItems == LearnosetNavItem.BuiltInItems.TOOLS) {
            learnosetNavItem.setTitle("Tools");
            learnosetNavItem.setIcon(R.drawable.tools_icon);
        } else if (builtInItems == LearnosetNavItem.BuiltInItems.SEARCH) {
            learnosetNavItem.setTitle("Search");
            learnosetNavItem.setIcon(R.drawable.search_icon);
        } else if (builtInItems == LearnosetNavItem.BuiltInItems.SHARE) {
            learnosetNavItem.setTitle("Share");
            learnosetNavItem.setIcon(R.drawable.share_icon);
        } else if (builtInItems == LearnosetNavItem.BuiltInItems.TRASH) {
            learnosetNavItem.setTitle("Trash");
            learnosetNavItem.setIcon(R.drawable.trash_icon);
        } else if (builtInItems == LearnosetNavItem.BuiltInItems.PROFILE) {
            learnosetNavItem.setTitle("Profile");
            learnosetNavItem.setIcon(R.drawable.profile_icon);
        }

        if (learnosetNavItems.size() == 0) {
            learnosetNavItem.setSelected(true);
        }

        learnosetNavItems.add(learnosetNavItem);
        navigationAdapter.reloadNavigationBar(iconsColor, selectedTheme, selectedItemBackgroundColor, navItemTxtColor, navGroupTxtColor);
    }

    public void addNavItem(LearnosetNavItem.BuiltInItems builtInItems, Fragment fragment, int fragmentContainerResId) {

        LearnosetNavItem learnosetNavItem = new LearnosetNavItem();
        learnosetNavItem.setFragment(fragment, fragmentContainerResId);

        if (builtInItems == LearnosetNavItem.BuiltInItems.DASHBOARD) {
            learnosetNavItem.setTitle("Dashboard");
            learnosetNavItem.setIcon(R.drawable.dashboard_icon);
        } else if (builtInItems == LearnosetNavItem.BuiltInItems.HOME) {
            learnosetNavItem.setTitle("Home");
            learnosetNavItem.setIcon(R.drawable.home_icon);
        } else if (builtInItems == LearnosetNavItem.BuiltInItems.SEND) {
            learnosetNavItem.setTitle("Send");
            learnosetNavItem.setIcon(R.drawable.send_icon);
        } else if (builtInItems == LearnosetNavItem.BuiltInItems.SETTINGS) {
            learnosetNavItem.setTitle("Settings");
            learnosetNavItem.setIcon(R.drawable.settings_icon);
        } else if (builtInItems == LearnosetNavItem.BuiltInItems.ABOUT_US) {
            learnosetNavItem.setTitle("About Us");
            learnosetNavItem.setIcon(R.drawable.about_us_icon);
        } else if (builtInItems == LearnosetNavItem.BuiltInItems.CONTACT_US) {
            learnosetNavItem.setTitle("Contact Us");
            learnosetNavItem.setIcon(R.drawable.contact_us_icon);
        } else if (builtInItems == LearnosetNavItem.BuiltInItems.DOWNLOAD) {
            learnosetNavItem.setTitle("Downloads");
            learnosetNavItem.setIcon(R.drawable.download_icon);
        } else if (builtInItems == LearnosetNavItem.BuiltInItems.EMAIL) {
            learnosetNavItem.setTitle("Email");
            learnosetNavItem.setIcon(R.drawable.email_icon);
        } else if (builtInItems == LearnosetNavItem.BuiltInItems.FAVOURITES) {
            learnosetNavItem.setTitle("Favourites");
            learnosetNavItem.setIcon(R.drawable.favourite_icon);
        } else if (builtInItems == LearnosetNavItem.BuiltInItems.GALLERY) {
            learnosetNavItem.setTitle("Gallery");
            learnosetNavItem.setIcon(R.drawable.gallery_icon);
        } else if (builtInItems == LearnosetNavItem.BuiltInItems.HELP) {
            learnosetNavItem.setTitle("Help");
            learnosetNavItem.setIcon(R.drawable.help_icon);
        } else if (builtInItems == LearnosetNavItem.BuiltInItems.MESSAGE) {
            learnosetNavItem.setTitle("Message");
            learnosetNavItem.setIcon(R.drawable.message_icon);
        } else if (builtInItems == LearnosetNavItem.BuiltInItems.FEEDBACK) {
            learnosetNavItem.setTitle("Feedback");
            learnosetNavItem.setIcon(R.drawable.feedback_icon);
        } else if (builtInItems == LearnosetNavItem.BuiltInItems.PRIVACY_POLICY) {
            learnosetNavItem.setTitle("Privacy Policy");
            learnosetNavItem.setIcon(R.drawable.privacy_policy_icon);
        } else if (builtInItems == LearnosetNavItem.BuiltInItems.RATE_US) {
            learnosetNavItem.setTitle("Rate Us");
            learnosetNavItem.setIcon(R.drawable.rate_us_icon);
        } else if (builtInItems == LearnosetNavItem.BuiltInItems.UPLOAD) {
            learnosetNavItem.setTitle("Upload");
            learnosetNavItem.setIcon(R.drawable.upload_icon);
        } else if (builtInItems == LearnosetNavItem.BuiltInItems.TOOLS) {
            learnosetNavItem.setTitle("Tools");
            learnosetNavItem.setIcon(R.drawable.tools_icon);
        } else if (builtInItems == LearnosetNavItem.BuiltInItems.SEARCH) {
            learnosetNavItem.setTitle("Search");
            learnosetNavItem.setIcon(R.drawable.search_icon);
        } else if (builtInItems == LearnosetNavItem.BuiltInItems.SHARE) {
            learnosetNavItem.setTitle("Share");
            learnosetNavItem.setIcon(R.drawable.share_icon);
        } else if (builtInItems == LearnosetNavItem.BuiltInItems.TRASH) {
            learnosetNavItem.setTitle("Trash");
            learnosetNavItem.setIcon(R.drawable.trash_icon);
        } else if (builtInItems == LearnosetNavItem.BuiltInItems.PROFILE) {
            learnosetNavItem.setTitle("Profile");
            learnosetNavItem.setIcon(R.drawable.profile_icon);
        }

        if (learnosetNavItems.size() == 0) {
            learnosetNavItem.setSelected(true);
        }

        learnosetNavItems.add(learnosetNavItem);
        navigationAdapter.reloadNavigationBar(iconsColor, selectedTheme, selectedItemBackgroundColor, navItemTxtColor, navGroupTxtColor);
    }

    public void addNavItem(LearnosetNavItem learnosetNavItem) {
        learnosetNavItems.add(learnosetNavItem);
        navigationAdapter.reloadNavigationBar(iconsColor, selectedTheme, selectedItemBackgroundColor, navItemTxtColor, navGroupTxtColor);
    }

    public void addNavItems(List<LearnosetNavItem> learnosetNavItem) {

        if (learnosetNavItems.size() == 0) {
            learnosetNavItem.get(0).setSelected(true);
        }

        learnosetNavItems.addAll(learnosetNavItem);
        navigationAdapter.reloadNavigationBar(iconsColor, selectedTheme, selectedItemBackgroundColor, navItemTxtColor, navGroupTxtColor);
    }

    public void addItemsGroup(NavItemsGroup navItemsGroup) {

        if (learnosetNavItems.size() == 0) {
            navItemsGroup.getGroupItems().get(0).setSelected(true);
        }

        navItemsGroups.add(navItemsGroup);
        learnosetNavItems.addAll(navItemsGroup.getGroupItems());
        navigationAdapter.reloadNavigationBar(iconsColor, selectedTheme, selectedItemBackgroundColor, navItemTxtColor, navGroupTxtColor);
    }

    public void removeItem(int itemPosition) {
        learnosetNavItems.remove(itemPosition);
        navigationAdapter.reloadNavigationBar(iconsColor, selectedTheme, selectedItemBackgroundColor, navItemTxtColor, navGroupTxtColor);
    }

    public void setBackgroundColor(int color) {
    }

    public void enableLogOutBtn(boolean enable) {
        if (enable) {
            navLogOutLayout.setVisibility(View.VISIBLE);
        } else {
            navLogOutLayout.setVisibility(View.GONE);
        }
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

        navigationAdapter.reloadNavigationBar(iconsColor, selectedTheme, selectedItemBackgroundColor, navItemTxtColor, navGroupTxtColor);
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
        headerRootLayout = headerView.findViewById(R.id.headerRootLayout);
        wishMessage = headerView.findViewById(R.id.headerWishMessage);

        wishMessage.setText(generateWishMessage());

        final RecyclerView navItemsRecyclerView = bodyView.findViewById(R.id.navItemsRecyclerView);
        bodyRootLayout = bodyView.findViewById(R.id.bodyRootLayout);
        navLogOutLayout = bodyView.findViewById(R.id.navLogOutLayout);
        navLogOutTxt = bodyView.findViewById(R.id.navLogOutTxt);

        navItemsRecyclerView.setHasFixedSize(true);
        navItemsRecyclerView.setLayoutManager(new LinearLayoutManager(context));

        navigationAdapter = new NavigationAdapter(context, learnosetNavItems, navItemsGroups, iconsColor, selectedTheme, selectedItemBackgroundColor, navigationEventListener, navItemTxtColor, navGroupTxtColor);
        navItemsRecyclerView.setAdapter(navigationAdapter);

        navLogOutLayout.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                navigationEventListener.onLogOutBtnClick();
            }
        });

        navigationEventListener = null;
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

    public enum NavColors {
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

    public enum DrawerGravity {
        LEFT,
        RIGHT
    }

    public enum NavThemes {
        LIGHT,
        DARK
    }
}
