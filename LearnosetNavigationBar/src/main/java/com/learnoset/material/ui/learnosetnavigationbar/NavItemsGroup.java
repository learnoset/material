package com.learnoset.material.ui.learnosetnavigationbar;

import java.util.ArrayList;
import java.util.List;

public class NavItemsGroup {

    private String groupName;
    private List<LearnosetNavItems> learnosetNavItems = null;
    public int GROUP_ID = 0;

    public NavItemsGroup(String groupName) {
        learnosetNavItems = new ArrayList<>();
        this.groupName = groupName;
        
        LearnosetNavigationBar.GROUPS_COUNT++;
        GROUP_ID = LearnosetNavigationBar.GROUPS_COUNT;
    }

    public NavItemsGroup(List<LearnosetNavItems> learnosetNavItems, String groupName) {
        this.learnosetNavItems = learnosetNavItems;
        this.groupName = groupName;

        LearnosetNavigationBar.GROUPS_COUNT++;
        GROUP_ID = LearnosetNavigationBar.GROUPS_COUNT;
    }

    public NavItemsGroup(LearnosetNavItems learnosetNavItem, String groupName) {
        if (learnosetNavItems == null) {
            learnosetNavItems = new ArrayList<>();
        }
        this.learnosetNavItems.add(learnosetNavItem);
        this.groupName = groupName;

        LearnosetNavigationBar.GROUPS_COUNT++;
        GROUP_ID = LearnosetNavigationBar.GROUPS_COUNT;
    }

    public void addItem(LearnosetNavItems learnosetNavItem) {
        learnosetNavItems.add(learnosetNavItem);
    }

    public void addItems(List<LearnosetNavItems> learnosetNavItemss) {
        learnosetNavItemss.addAll(learnosetNavItems);
    }

    public List<LearnosetNavItems> getLearnosetNavItems() {
        return learnosetNavItems;
    }

    public void setLearnosetNavItems(List<LearnosetNavItems> learnosetNavItems) {
        this.learnosetNavItems = learnosetNavItems;
    }
}