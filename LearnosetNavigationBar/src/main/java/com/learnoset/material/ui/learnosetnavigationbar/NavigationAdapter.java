package com.learnoset.material.ui.learnosetnavigationbar;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.graphics.drawable.DrawableCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class NavigationAdapter extends RecyclerView.Adapter<NavigationAdapter.MyViewHolder> {

    private final List<LearnosetNavItem> learnosetNavItems;
    private final List<NavItemsGroup> navItemsGroups;
    private final Context context;
    private final NavigationEventListener navigationEventListener;
    private final List<Integer> groupIds = new ArrayList<>();
    private int iconsColor;
    private int navItemsTxtColor;
    private int navGroupTxtColor;
    private int selectedItemBackgroundColor;
    private LearnosetNavigationBar.NavThemes selectedNavTheme;
    private DrawerLayout drawerLayout;

    public NavigationAdapter(Context context, List<LearnosetNavItem> learnosetNavItems, List<NavItemsGroup> navItemsGroups, int iconsColor, LearnosetNavigationBar.NavThemes selectedNavTheme, int selectedItemBackgroundColor, NavigationEventListener navigationEventListener, int navItemsTxtColor, int navGroupTxtColor) {

        this.drawerLayout = null;
        this.selectedNavTheme = selectedNavTheme;
        this.learnosetNavItems = learnosetNavItems;
        this.navItemsGroups = navItemsGroups;
        this.navGroupTxtColor = navGroupTxtColor;
        this.context = context;
        this.navigationEventListener = navigationEventListener;
        this.iconsColor = iconsColor;
        this.navItemsTxtColor = navItemsTxtColor;
        this.selectedItemBackgroundColor = selectedItemBackgroundColor;
    }

    public void setDrawerLayout(DrawerLayout drawerLayout) {
        this.drawerLayout = drawerLayout;
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
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.nav_adapter_layout, null));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        LearnosetNavItem learnosetNavItem = learnosetNavItems.get(position);

        holder.navItemTitle.setText(learnosetNavItem.getTitle());
        holder.navItemIcon.setImageResource(learnosetNavItem.getIcon());

        if (learnosetNavItem.isSelected()) {
            LearnosetNavigationBar.selectedItemPosition = position;

            holder.navItemLayout.setBackground(createRoundBackground());
            DrawableCompat.setTint(holder.navItemIcon.getDrawable(), Color.parseColor("#FFFFFF"));
            holder.navItemTitle.setTextColor(Color.WHITE);

            if (learnosetNavItem.getFragment() != null) {
                FragmentManager fragmentManager = ((FragmentActivity) context).getSupportFragmentManager();
                fragmentManager.beginTransaction()
                        .replace(learnosetNavItem.getFragmentContainerResId(), learnosetNavItem.getFragment(), null)
                        .setReorderingAllowed(true)
                        .addToBackStack(null)
                        .commit();
            }
        } else {
            holder.navItemLayout.setBackgroundColor(Color.TRANSPARENT);
            DrawableCompat.setTint(holder.navItemIcon.getDrawable(), iconsColor);

            holder.navItemTitle.setTextColor(navItemsTxtColor);

        }

        if (learnosetNavItem.groupId != -1 && !checkGroupAdded(learnosetNavItem.groupId)) {
            groupIds.add(learnosetNavItem.groupId);
            holder.groupName.setText(getGroupName(learnosetNavItem.groupId));
            holder.groupName.setVisibility(View.VISIBLE);
            holder.groupName.setTextColor(navGroupTxtColor);

        } else {
            holder.groupName.setVisibility(View.GONE);
        }

        holder.navItemLayout.setOnClickListener(v -> {
            learnosetNavItems.get(LearnosetNavigationBar.selectedItemPosition).setSelected(false);
            learnosetNavItems.get(position).setSelected(true);

            reloadNavigationBar(iconsColor, selectedNavTheme, selectedItemBackgroundColor, navItemsTxtColor, navGroupTxtColor);

            if (navigationEventListener != null) {
                navigationEventListener.onItemSelected(position, learnosetNavItem);
            }

            if (drawerLayout != null) {
                drawerLayout.closeDrawer(LearnosetNavigationBar.drawerGravity);
            }

            if (learnosetNavItem.getFragment() != null) {
                FragmentManager fragmentManager = ((FragmentActivity) context).getSupportFragmentManager();
                fragmentManager.beginTransaction()
                        .replace(learnosetNavItem.getFragmentContainerResId(), learnosetNavItem.getFragment(), null)
                        .setReorderingAllowed(true)
                        .addToBackStack(null)
                        .commit();
            }
        });

    }

    private boolean checkGroupAdded(int groupId) {

        boolean isGroupAdded = false;
        for (int i = 0; i < groupIds.size(); i++) {
            if (groupIds.get(i) == groupId) {
                isGroupAdded = true;
                break;
            }
        }
        return isGroupAdded;
    }

    public void reloadNavigationBar(int iconsColor, LearnosetNavigationBar.NavThemes selectedNavTheme, int selectedItemBackgroundColor, int navItemsTxtColor, int navGroupTxtColor) {
        this.iconsColor = iconsColor;
        this.selectedNavTheme = selectedNavTheme;
        this.selectedItemBackgroundColor = selectedItemBackgroundColor;
        this.navItemsTxtColor = navItemsTxtColor;
        this.navGroupTxtColor = navGroupTxtColor;

        groupIds.clear();
        notifyDataSetChanged();
    }

    private Drawable createRoundBackground() {

        GradientDrawable shape = new GradientDrawable();
        shape.setShape(GradientDrawable.RECTANGLE);
        shape.setColor(selectedItemBackgroundColor);
        shape.setCornerRadius(20);

        return shape;
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
        private final RelativeLayout navItemLayout;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            groupName = itemView.findViewById(R.id.groupName);
            navItemIcon = itemView.findViewById(R.id.navItemIcon);
            navItemTitle = itemView.findViewById(R.id.navItemTitle);
            navItemLayout = itemView.findViewById(R.id.navItemLayout);
        }
    }
}
