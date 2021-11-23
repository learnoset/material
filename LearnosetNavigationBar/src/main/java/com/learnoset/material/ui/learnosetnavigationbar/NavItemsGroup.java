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
        } else if (builtInItems == LearnosetNavItem.BuiltInItems.HOME) {
            learnosetNavItem.setTitle("Home");
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
