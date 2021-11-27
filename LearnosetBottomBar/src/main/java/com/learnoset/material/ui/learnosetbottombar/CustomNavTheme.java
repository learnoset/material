package com.learnoset.material.ui.learnosetbottombar;

import android.graphics.Color;

public class CustomNavTheme {

    private int iconsColor;
    private int backgroundColor;
    private int selectedItemBackgroundColor;
    private int selectedItemTextColor;
    private int selectedItemIconColor;

    public CustomNavTheme() {
        iconsColor = 0;
        selectedItemBackgroundColor = 0;
        selectedItemTextColor = 0;
        selectedItemIconColor = 0;
    }

    public CustomNavTheme(int iconsColor, int backgroundColor, int selectedItemBackgroundColor, int selectedItemTextColor, int selectedItemIconColor) {
        this.iconsColor = iconsColor;
        this.backgroundColor = backgroundColor;
        this.selectedItemBackgroundColor = selectedItemBackgroundColor;
        this.selectedItemTextColor = selectedItemTextColor;
        this.selectedItemIconColor = selectedItemIconColor;
    }

    public int getIconsColor() {
        return iconsColor;
    }

    public void setIconsColor(int iconsColor) {
        this.iconsColor = iconsColor;
    }

    public void setIconsColor(LearnosetBottomBar.LearnosetColors iconsColor) {
        this.iconsColor = Color.parseColor(getColorValue(iconsColor));
    }

    public int getBackgroundColor() {
        return backgroundColor;
    }

    public void setBackgroundColor(int backgroundColor) {
        this.backgroundColor = backgroundColor;
    }

    public void setBackgroundColor(LearnosetBottomBar.LearnosetColors backgroundColor) {
        this.backgroundColor = Color.parseColor(getColorValue(backgroundColor));
    }

    public int getSelectedItemBackgroundColor() {
        return selectedItemBackgroundColor;
    }

    public void setSelectedItemBackgroundColor(int selectedItemBackgroundColor) {
        this.selectedItemBackgroundColor = selectedItemBackgroundColor;
    }

    public void setSelectedItemBackgroundColor(LearnosetBottomBar.LearnosetColors selectedItemBackgroundColor) {
        this.selectedItemBackgroundColor = Color.parseColor(getColorValue(selectedItemBackgroundColor));
    }

    public int getSelectedItemTextColor() {
        return selectedItemTextColor;
    }

    public void setSelectedItemTextColor(int selectedItemTextColor) {
        this.selectedItemTextColor = selectedItemTextColor;
    }

    public void setSelectedItemTextColor(LearnosetBottomBar.LearnosetColors selectedItemTextColor) {
        this.selectedItemTextColor = Color.parseColor(getColorValue(selectedItemTextColor));
    }

    public int getSelectedItemIconColor() {
        return selectedItemIconColor;
    }

    public void setSelectedItemIconColor(int selectedItemIconColor) {
        this.selectedItemIconColor = selectedItemIconColor;
    }

    public void setSelectedItemIconColor(LearnosetBottomBar.LearnosetColors selectedItemIconColor) {
        this.selectedItemIconColor = Color.parseColor(getColorValue(selectedItemIconColor));
    }

    private String getColorValue(LearnosetBottomBar.LearnosetColors navColor) {

        String selectedColorValue = "";

        if (navColor == LearnosetBottomBar.LearnosetColors.RED) {
            selectedColorValue = "#FFFF1744";
        } else if (navColor == LearnosetBottomBar.LearnosetColors.BLACK) {
            selectedColorValue = "#000000";
        } else if (navColor == LearnosetBottomBar.LearnosetColors.GRAY) {
            selectedColorValue = "#998A8A8A";
        } else if (navColor == LearnosetBottomBar.LearnosetColors.ORANGE) {
            selectedColorValue = "#FF9100";
        } else if (navColor == LearnosetBottomBar.LearnosetColors.WHITE) {
            selectedColorValue = "#FFFFFF";
        } else if (navColor == LearnosetBottomBar.LearnosetColors.YELLOW) {
            selectedColorValue = "#FFEA00";
        } else if (navColor == LearnosetBottomBar.LearnosetColors.DARK_RED) {
            selectedColorValue = "#FFD50000";
        } else if (navColor == LearnosetBottomBar.LearnosetColors.LIGHT_RED) {
            selectedColorValue = "#FFFF8A80";
        } else if (navColor == LearnosetBottomBar.LearnosetColors.DARK_ORANGE) {
            selectedColorValue = "#FFFF6D00";
        } else if (navColor == LearnosetBottomBar.LearnosetColors.LIGHT_ORANGE) {
            selectedColorValue = "#FFFFD180";
        } else if (navColor == LearnosetBottomBar.LearnosetColors.BLUE) {
            selectedColorValue = "#FF00B0FF";
        } else if (navColor == LearnosetBottomBar.LearnosetColors.DARK_BLUE) {
            selectedColorValue = "#FF0091EA";
        } else if (navColor == LearnosetBottomBar.LearnosetColors.LIGHT_BLUE) {
            selectedColorValue = "#FF80D8FF";
        }

        return selectedColorValue;
    }

}
