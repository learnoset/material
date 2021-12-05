package com.learnoset.material.ui.learnosetnavigationbar;

import android.graphics.Color;

public class CustomNavTheme {


    private int textColor;
    private int iconsColor;
    private int navigationBackground;
    private int groupNameColor;
    private int selectedItemBackgroundColor;
    private int selectedItemTextColor;
    private int selectedItemIconColor;


    public CustomNavTheme() {
        textColor = 0;
        iconsColor = 0;
        navigationBackground = 0;
        groupNameColor = 0;
        selectedItemBackgroundColor = 0;
        selectedItemTextColor = 0;
        selectedItemIconColor = 0;
    }

    public int getTextColor() {
        return textColor;
    }

    public void setTextColor(int textColor) {
        this.textColor = textColor;
    }

    public void setTextColor(LearnosetNavigationBar.NavColors textColor) {
        this.textColor = Color.parseColor(getNavColorValue(textColor));
    }

    public int getIconsColor() {
        return iconsColor;
    }

    public void setIconsColor(int iconsColor) {
        this.iconsColor = iconsColor;
    }

    public void setIconsColor(LearnosetNavigationBar.NavColors iconsColor) {
        this.iconsColor = Color.parseColor(getNavColorValue(iconsColor));
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

    public void setGroupNameColor(LearnosetNavigationBar.NavColors groupNameColor) {
        this.groupNameColor = Color.parseColor(getNavColorValue(groupNameColor));
    }

    public int getSelectedItemBackgroundColor() {
        return selectedItemBackgroundColor;
    }

    public void setSelectedItemBackgroundColor(int selectedItemBackgroundColor) {
        this.selectedItemBackgroundColor = selectedItemBackgroundColor;
    }

    public void setSelectedItemBackgroundColor(LearnosetNavigationBar.NavColors selectedItemBackgroundColor) {
        this.selectedItemBackgroundColor = Color.parseColor(getNavColorValue(selectedItemBackgroundColor));
    }

    public int getSelectedItemTextColor() {
        return selectedItemTextColor;
    }

    public void setSelectedItemTextColor(int selectedItemTextColor) {
        this.selectedItemTextColor = selectedItemTextColor;
    }

    public void setSelectedItemTextColor(LearnosetNavigationBar.NavColors selectedItemTextColor) {
        this.selectedItemTextColor = Color.parseColor(getNavColorValue(selectedItemTextColor));
    }

    public int getSelectedItemIconColor() {
        return selectedItemIconColor;
    }

    public void setSelectedItemIconColor(int selectedItemIconColor) {
        this.selectedItemIconColor = selectedItemIconColor;
    }

    public void setSelectedItemIconColor(LearnosetNavigationBar.NavColors selectedItemIconColor) {
        this.selectedItemIconColor = Color.parseColor(getNavColorValue(selectedItemIconColor));
    }

    private String getNavColorValue(LearnosetNavigationBar.NavColors navColor) {

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

}
