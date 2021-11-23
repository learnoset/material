package com.learnoset.material.ui.learnosetnavigationbar;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.drawable.DrawableCompat;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class NavigationAdapter extends RecyclerView.Adapter<NavigationAdapter.MyViewHolder> {

    private final List<LearnosetNavItem> learnosetNavItems;
    private final List<NavItemsGroup> navItemsGroups;
    private final Context context;
    private String iconColorCode = "#CC000000";
    private int groupId = -1;

    public NavigationAdapter(Context context, List<LearnosetNavItem> learnosetNavItems, List<NavItemsGroup> navItemsGroups, LearnosetNavigationBar.IconColors selectedIconColor, LearnosetNavigationBar.NavThemes selectedNavTheme) {

        this.learnosetNavItems = learnosetNavItems;
        this.navItemsGroups = navItemsGroups;
        this.context = context;

        if (selectedIconColor == LearnosetNavigationBar.IconColors.RED) {
            iconColorCode = "#FFFF1744";
        } else if (selectedIconColor == LearnosetNavigationBar.IconColors.BLACK) {
            iconColorCode = "#000000";
        } else if (selectedIconColor == LearnosetNavigationBar.IconColors.GRAY) {
            iconColorCode = "#998A8A8A";
        } else if (selectedIconColor == LearnosetNavigationBar.IconColors.ORANGE) {
            iconColorCode = "#FF9100";
        } else if (selectedIconColor == LearnosetNavigationBar.IconColors.WHITE) {
            iconColorCode = "#FFFFFF";
        } else if (selectedIconColor == LearnosetNavigationBar.IconColors.YELLOW) {
            iconColorCode = "#FFEA00";
        } else if (selectedIconColor == LearnosetNavigationBar.IconColors.DARK_RED) {
            iconColorCode = "#FFD50000";
        } else if (selectedIconColor == LearnosetNavigationBar.IconColors.LIGHT_RED) {
            iconColorCode = "#FFFF8A80";
        } else if (selectedIconColor == LearnosetNavigationBar.IconColors.DARK_ORANGE) {
            iconColorCode = "#FFFF6D00";
        } else if (selectedIconColor == LearnosetNavigationBar.IconColors.LIGHT_ORANGE) {
            iconColorCode = "#FFFFD180";
        } else if (selectedIconColor == LearnosetNavigationBar.IconColors.BLUE) {
            iconColorCode = "#FF00B0FF";
        } else if (selectedIconColor == LearnosetNavigationBar.IconColors.DARK_BLUE) {
            iconColorCode = "#FF0091EA";
        } else if (selectedIconColor == LearnosetNavigationBar.IconColors.LIGHT_BLUE) {
            iconColorCode = "#FF80D8FF";
        } else {
            if (selectedNavTheme == LearnosetNavigationBar.NavThemes.DARK) {
                iconColorCode = "#FFFFFF";
            } else {
                iconColorCode = "#CC000000";
            }
        }
    }

    public void addNewItem(LearnosetNavItem learnosetNavItem) {
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

        LearnosetNavItem learnosetNavItem = learnosetNavItems.get(position);

        holder.navItemTitle.setText(learnosetNavItem.getTitle());
        holder.navItemIcon.setImageResource(learnosetNavItem.getIcon());
        
        DrawableCompat.setTint(holder.navItemIcon.getDrawable(), Color.parseColor(iconColorCode));

        if (groupId != learnosetNavItem.groupId) {
            groupId = learnosetNavItem.groupId;
            holder.groupName.setText(getGroupName(learnosetNavItem.groupId));
            holder.groupName.setVisibility(View.VISIBLE);
        } else {
            holder.groupName.setVisibility(View.GONE);
        }

    }

    private String getGroupName(int groupId) {

        String groupName = "";

        for (int i = 0; i < navItemsGroups.size(); i++) {
            if (navItemsGroups.get(i).id == groupId) {
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

        private final TextView navItemTitle;
        private final ImageView navItemIcon;
        private final TextView groupName;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            groupName = itemView.findViewById(R.id.groupName);
            navItemIcon = itemView.findViewById(R.id.navItemIcon);
            navItemTitle = itemView.findViewById(R.id.navItemTitle);
        }
    }
}
