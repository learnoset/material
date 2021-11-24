package com.learnoset.material.ui.learnosetnavigationbar;

public class CustomNavTheme {

    private int textColor;
    private int iconsColor;
    private int navigationBackground;
    private int groupNameColor;
    private int selectedItemBackgroundColor;

    public CustomNavTheme() {
        textColor = 0;
        iconsColor = 0;
        navigationBackground = 0;
        groupNameColor = 0;
        selectedItemBackgroundColor = 0;
    }

    public int getTextColor() {
        return textColor;
    }

    public void setTextColor(int textColor) {
        this.textColor = textColor;
    }

    public int getIconsColor() {
        return iconsColor;
    }

    public void setIconsColor(int iconsColor) {
        this.iconsColor = iconsColor;
    }

    public int getNavigationBackground() {
        return navigationBackground;
    }

    public void setNavigationBackground(int navigationBackgroundColor) {
        this.navigationBackground = navigationBackgroundColor;
    }

    public int getGroupNameColor() {
        return groupNameColor;
    }

    public void setGroupNameColor(int groupNameColor) {
        this.groupNameColor = groupNameColor;
    }

    public int getSelectedItemBackgroundColor() {
        return selectedItemBackgroundColor;
    }

    public void setSelectedItemBackgroundColor(int selectedItemBackgroundColor) {
        this.selectedItemBackgroundColor = selectedItemBackgroundColor;
    }
}
