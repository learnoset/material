package com.learnoset.material.ui.learnosetnavigationbar;

public class LearnosetNavItem {

    protected int groupId;
    private int itemIcon = R.drawable.error_icon;
    private String itemName = "";
    private NavItemsGroup navItemsGroup;

    public LearnosetNavItem() {
        this.groupId = -1;
    }

    public void setTitle(String itemTitle){
        this.itemName = itemTitle;
    }

    public void setIcon(int itemResIconId){
        this.itemIcon = itemIcon;
    }

    public String getTitle() {
        return itemName;
    }

    public int getIcon() {
        return itemIcon;
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
