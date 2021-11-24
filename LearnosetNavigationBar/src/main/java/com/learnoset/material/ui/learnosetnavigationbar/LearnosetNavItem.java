package com.learnoset.material.ui.learnosetnavigationbar;

import androidx.fragment.app.Fragment;

public class LearnosetNavItem {

    protected int groupId;
    private int itemIcon = R.drawable.error_icon;
    private String itemName = "";
    private NavItemsGroup navItemsGroup;
    private boolean selected = false;
    private Fragment fragment;
    private int fragmentContainerResId;

    public LearnosetNavItem() {
        this.groupId = -1;
        fragment = null;
    }

    public LearnosetNavItem(String itemTitle, int itemResIconId) {
        this.groupId = -1;
        this.itemName = itemTitle;
        this.itemIcon = itemResIconId;
        fragment = null;
    }

    public LearnosetNavItem(String itemTitle, int itemResIconId, Fragment fragment, int fragmentContainerResId) {
        this.groupId = -1;
        this.itemName = itemTitle;
        this.itemIcon = itemResIconId;
        this.fragment = fragment;
        this.fragmentContainerResId = fragmentContainerResId;
    }

    public LearnosetNavItem(String itemTitle , Fragment fragment, int fragmentContainerResId) {
        this.groupId = -1;
        this.itemName = itemTitle;
        this.fragment = fragment;
        this.fragmentContainerResId = fragmentContainerResId;
    }

    public void setFragment(Fragment fragment, int fragmentContainerResId){
        this.fragment = fragment;
        this.fragmentContainerResId = fragmentContainerResId;
    }

    public Fragment getFragment(){
        return fragment;
    }

    public int getFragmentContainerResId(){
        return fragmentContainerResId;
    }

    public void setTitle(String itemTitle){
        this.itemName = itemTitle;
    }

    public void setIcon(int itemResIconId){
        this.itemIcon = itemResIconId;
    }

    public String getTitle() {
        return itemName;
    }

    public int getIcon() {
        return itemIcon;
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
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
