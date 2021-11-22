package com.learnoset.material.ui.learnosetnavigationbar;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class NavigationAdapter extends RecyclerView.Adapter<NavigationAdapter.MyViewHolder> {

    private final List<LearnosetNavItems> learnosetNavItems;
    private final Context context;

    public NavigationAdapter(List<LearnosetNavItems> learnosetNavItems, Context context) {
        this.learnosetNavItems = learnosetNavItems;
        this.context = context;
    }

    public void addNewItem(LearnosetNavItems learnosetNavItem) {
        learnosetNavItems.add(learnosetNavItem);
    }

    public void removeItem(int position) {
        learnosetNavItems.remove(position);
    }

    @NonNull
    @Override
    public NavigationAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull NavigationAdapter.MyViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    static class MyViewHolder extends RecyclerView.ViewHolder {
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
