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
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class NavigationAdapter extends RecyclerView.Adapter<NavigationAdapter.MyViewHolder> {

    private final List<LearnosetNavItem> learnosetNavItems;
    private final List<NavItemsGroup> navItemsGroups;
    private final Context context;
    private final NavigationEventListener navigationEventListener;
    private String iconColorCode;
    private String selectedItemBackgroundColor;
    private int groupId = -1;
    private LearnosetNavigationBar.NavColors selectedIconColor;
    private LearnosetNavigationBar.NavThemes selectedNavTheme;
    private LearnosetNavigationBar.NavColors selectedItemBackground;

    public NavigationAdapter(Context context, List<LearnosetNavItem> learnosetNavItems, List<NavItemsGroup> navItemsGroups, LearnosetNavigationBar.NavColors selectedIconColor, LearnosetNavigationBar.NavThemes selectedNavTheme, LearnosetNavigationBar.NavColors selectedItemBackground, NavigationEventListener navigationEventListener) {

        this.selectedIconColor = selectedIconColor;
        this.selectedNavTheme = selectedNavTheme;
        this.selectedItemBackground = selectedItemBackground;
        this.learnosetNavItems = learnosetNavItems;
        this.navItemsGroups = navItemsGroups;
        this.context = context;
        this.navigationEventListener = navigationEventListener;

        gettingSelectedThemeDetails();
    }

    public void addNewItem(LearnosetNavItem learnosetNavItem) {
        learnosetNavItems.add(learnosetNavItem);
    }

    private void gettingSelectedThemeDetails() {

        if (selectedIconColor == LearnosetNavigationBar.NavColors.DEFAULT) {
            if (selectedNavTheme == LearnosetNavigationBar.NavThemes.DARK) {
                iconColorCode = "#E6FFFFFF";
            } else {
                iconColorCode = "#99000000";
            }
        } else {
            this.iconColorCode = getNavColorValue(selectedIconColor);
        }

        if (selectedItemBackground == LearnosetNavigationBar.NavColors.DEFAULT) {

            if (selectedNavTheme == LearnosetNavigationBar.NavThemes.DARK) {
                this.selectedItemBackgroundColor = "#4C74FA";
            } else {
                this.selectedItemBackgroundColor = "#4C74FA";
            }
        } else {
            this.selectedItemBackgroundColor = getNavColorValue(selectedItemBackground);
        }

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

        if (learnosetNavItem.isSelected()) {
            LearnosetNavigationBar.selectedItemPosition = position;

            holder.navItemLayout.setBackground(createRoundBackground());
            DrawableCompat.setTint(holder.navItemIcon.getDrawable(), Color.parseColor("#FFFFFF"));
            holder.navItemTitle.setTextColor(Color.WHITE);
        } else {
            holder.navItemLayout.setBackgroundColor(Color.TRANSPARENT);
            DrawableCompat.setTint(holder.navItemIcon.getDrawable(), Color.parseColor(iconColorCode));

            if (selectedNavTheme == LearnosetNavigationBar.NavThemes.DARK) {
                holder.navItemTitle.setTextColor(Color.parseColor("#E6FFFFFF"));
            } else {
                holder.navItemTitle.setTextColor(Color.parseColor("#99000000"));
            }

        }

        if (groupId < learnosetNavItem.groupId) {

            groupId = learnosetNavItem.groupId;
            holder.groupName.setText(getGroupName(learnosetNavItem.groupId));
            holder.groupName.setVisibility(View.VISIBLE);

            if (selectedNavTheme == LearnosetNavigationBar.NavThemes.DARK) {
                holder.groupName.setTextColor(Color.parseColor("#66FFFFFF"));
            } else {
                holder.groupName.setTextColor(Color.parseColor("#66000000"));
            }
        } else {
            holder.groupName.setVisibility(View.GONE);
        }

        holder.navItemLayout.setOnClickListener(v -> {
            learnosetNavItems.get(LearnosetNavigationBar.selectedItemPosition).setSelected(false);
            learnosetNavItems.get(position).setSelected(true);

            reloadNavigationBar(selectedIconColor, selectedNavTheme, selectedItemBackground);

            if (navigationEventListener != null) {
                navigationEventListener.onItemSelected(position, learnosetNavItem);
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

    public void reloadNavigationBar(LearnosetNavigationBar.NavColors selectedIconColor, LearnosetNavigationBar.NavThemes selectedNavTheme, LearnosetNavigationBar.NavColors selectedItemBackground) {
        this.selectedIconColor = selectedIconColor;
        this.selectedNavTheme = selectedNavTheme;
        this.selectedItemBackground = selectedItemBackground;

        gettingSelectedThemeDetails();
        groupId = -1;
        notifyDataSetChanged();
    }

    private Drawable createRoundBackground() {

        GradientDrawable shape = new GradientDrawable();
        shape.setShape(GradientDrawable.RECTANGLE);
        shape.setColor(Color.parseColor(selectedItemBackgroundColor));
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
