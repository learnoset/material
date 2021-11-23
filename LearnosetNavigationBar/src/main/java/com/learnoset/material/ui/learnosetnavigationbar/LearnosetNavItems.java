package com.learnoset.material.ui.learnosetnavigationbar;

public class LearnosetNavItems {

    private int itemIcon = R.drawable.error_icon;
    private String itemName = "";
    private NavItemsGroup navItemsGroup;
    public int groupId = -1;

    public LearnosetNavItems() {
        this.groupId = -1;
    }

    public void addItem(BuiltInItems builtInItems) {
        if (builtInItems == BuiltInItems.DASHBOARD) {

        } else if (builtInItems == BuiltInItems.HOME) {

        }
    }

    public void addItem(String itemTitle, int itemIcon) {
        this.itemIcon = itemIcon;
        this.itemName = itemTitle;

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
