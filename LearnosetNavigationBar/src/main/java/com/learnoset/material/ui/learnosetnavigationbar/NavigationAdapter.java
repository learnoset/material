package com.learnoset.material.ui.learnosetnavigationbar;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class NavigationAdapter extends RecyclerView.Adapter<NavigationAdapter.MyViewHolder> {

    private final List<LearnosetNavItems> learnosetNavItems;
    private final List<NavItemsGroup> navItemsGroups;
    private final Context context;

    public NavigationAdapter(Context context, List<LearnosetNavItems> learnosetNavItems, List<NavItemsGroup> navItemsGroups) {
        this.learnosetNavItems = learnosetNavItems;
        this.navItemsGroups = navItemsGroups;
        this.context = context;
    }

    public void addNewItem(LearnosetNavItems learnosetNavItem) {
        learnosetNavItems.add(learnosetNavItem);
    }

    public void removeItem(int position) {
        learnosetNavItems.remove(position);
    }

    @SuppressLint("InflateParams")
    @NonNull
    @Override
    public NavigationAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.nav_adapter_layout, null));
    }

    @Override
    public void onBindViewHolder(@NonNull NavigationAdapter.MyViewHolder holder, int position) {

        LearnosetNavItems learnosetNavItem = learnosetNavItems.get(position);

        if (learnosetNavItem.groupId == -1) {
            holder.navItemTV.setText(learnosetNavItem.getTitle());
        } else {
            holder.navItemTV.setText("Group = " + getGroupName(learnosetNavItem.groupId) + "//" + learnosetNavItem.getTitle());
        }

    }

    private String getGroupName(int groupId) {

        String groupName = "";

        for (int i = 0; i < navItemsGroups.size(); i++) {
            if (navItemsGroups.get(i).GROUP_ID == groupId) {
                groupName = navItemsGroups.get(i).getGroupName();
                break;
            }
        }

        return groupName;
    }

    @Override
    public int getItemCount() {
        return learnosetNavItems.size();
    }

    static class MyViewHolder extends RecyclerView.ViewHolder {

        private final TextView navItemTV;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            navItemTV = itemView.findViewById(R.id.navItemTV);
        }
    }
}
