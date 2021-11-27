package com.learnoset.learnosetbottombar;

import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

public class BottomBarItem {

    public LinearLayout rootLayout;
    public ImageView icon;
    public TextView titleTextView;
    private String title;
    private int iconResourceId;
    private Fragment fragment;
    private int fragmentContainer;

    public BottomBarItem() {
        rootLayout = null;
        icon = null;
        titleTextView = null;
        title = "";
        iconResourceId = 0;
        fragment = null;
        fragmentContainer = 0;
    }

    public BottomBarItem(String title, int iconResourceId) {
        this.title = title;
        this.iconResourceId = iconResourceId;
        rootLayout = null;
        icon = null;
        titleTextView = null;
        fragment = null;
        fragmentContainer = 0;
    }

    public BottomBarItem(String title, int iconResourceId, Fragment fragment, int fragmentContainer) {
        this.title = title;
        this.iconResourceId = iconResourceId;
        rootLayout = null;
        icon = null;
        titleTextView = null;
        this.fragment = fragment;
        this.fragmentContainer = fragmentContainer;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getIcon() {
        return iconResourceId;
    }

    public void setIcon(int iconResourceId) {
        this.iconResourceId = iconResourceId;
    }

    public Fragment getFragment() {
        return fragment;
    }

    public void setFragment(Fragment fragment) {
        this.fragment = fragment;
    }

    public int getFragmentContainer() {
        return fragmentContainer;
    }

    public void setFragmentContainer(int fragmentContainer) {
        this.fragmentContainer = fragmentContainer;
    }

    public enum BuiltInItems {
        DASHBOARD,
        HOME,
        GALLERY,
        DOWNLOAD,
        TOOLS,
        SETTINGS,
        ABOUT_US,
        CONTACT_US,
        PRIVACY_POLICY,
        PROFILE,
        SHARE,
        EMAIL,
        SEND,
        MESSAGE,
        FAVOURITES,
        HELP,
        RATE_US,
        SEARCH,
        UPLOAD,
        FEEDBACK,
        TRASH
    }
}
