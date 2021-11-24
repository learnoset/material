package com.learnoset.material.ui.learnosetnavigationbar;

import java.util.ArrayList;
import java.util.List;

public class NavItemsGroup {

    public int id;
    private final String groupName;
    private final List<LearnosetNavItem> learnosetNavItems;

    public NavItemsGroup(String groupName) {
        learnosetNavItems = new ArrayList<>();
        this.groupName = groupName;

        LearnosetNavigationBar.GROUPS_COUNT++;
        id = LearnosetNavigationBar.GROUPS_COUNT;
    }

    public void addGroupItem(LearnosetNavItem learnosetNavItem) {
        learnosetNavItem.groupId = id;
        learnosetNavItems.add(learnosetNavItem);
    }

    public void addGroupItems(List<LearnosetNavItem> learnosetNavItemss) {
        for (int i = 0; i < learnosetNavItemss.size(); i++) {
            LearnosetNavItem learnosetNavItem = learnosetNavItemss.get(i);
            learnosetNavItem.groupId = id;
            learnosetNavItems.add(learnosetNavItem);
        }
    }

    public void addGroupItem(LearnosetNavItem.BuiltInItems builtInItems) {

        LearnosetNavItem learnosetNavItem = new LearnosetNavItem();
        learnosetNavItem.groupId = id;

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

        learnosetNavItems.add(learnosetNavItem);
    }

    public List<LearnosetNavItem> getGroupItems() {
        return learnosetNavItems;
    }

    public String getGroupName(){
        return groupName;
    }
}
