package com.learnoset.material.ui.learnosetbottombar;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.os.Build;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.core.graphics.drawable.DrawableCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;

import com.learnoset.learnosetbottombar.R;

import java.util.ArrayList;
import java.util.List;

public class LearnosetBottomBar extends LinearLayout implements View.OnClickListener {

    public static int selectedItem = 0;
    private final List<BottomBarItem> bottomBarItems = new ArrayList<>();
    private final Context context;
    private int totalBottomBarItems = 0;
    private BottomBarTheme bottomBarTheme = BottomBarTheme.LIGHT;
    private int iconsColor;
    private int bottomBarBackgroundColor;
    private int selectedItemBackgroundColor;
    private int selectedItemIconColor;
    private int selectedItemTextColor;
    private int textSize;
    private Thread thread;
    private boolean animationEnabled = true;

    private BottomBarEventListener bottomBarEventListener;

    public LearnosetBottomBar(Context context) {
        super(context);
        this.context = context;
        this.thread = null;
        bottomBarEventListener = null;
        init();
    }

    public LearnosetBottomBar(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        this.thread = null;
        bottomBarEventListener = null;
        init();
    }

    private void init() {

        gettingThemeValues();

        setPadding(convertDpToPixel(20), convertDpToPixel(10), convertDpToPixel(20), convertDpToPixel(10));

        setBackgroundColor(bottomBarBackgroundColor);

        setOrientation(HORIZONTAL);

        setGravity(Gravity.CENTER);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            setElevation(10);
        }

        selectedItem = 0;
    }

    public void setEventListener(BottomBarEventListener bottomBarEventListener) {
        this.bottomBarEventListener = bottomBarEventListener;
    }

    public void setTheme(BottomBarTheme setTheme) {
        bottomBarTheme = setTheme;
        gettingThemeValues();
        refreshBottomBar();
    }

    public void setTheme(CustomBottomBarTheme setCustomTheme) {

        if (setCustomTheme.getSelectedItemBackgroundColor() != 0) {
            selectedItemBackgroundColor = setCustomTheme.getSelectedItemBackgroundColor();
        }

        if (setCustomTheme.getIconsColor() != 0) {
            iconsColor = setCustomTheme.getIconsColor();
        }

        if (setCustomTheme.getBackgroundColor() != 0) {
            bottomBarBackgroundColor = setCustomTheme.getBackgroundColor();
        }

        if (setCustomTheme.getSelectedItemBackgroundColor() != 0) {
            selectedItemBackgroundColor = setCustomTheme.getSelectedItemBackgroundColor();
        }

        if (setCustomTheme.getSelectedItemIconColor() != 0) {
            selectedItemIconColor = setCustomTheme.getSelectedItemIconColor();
        }

        if (setCustomTheme.getSelectedItemTextColor() != 0) {
            selectedItemTextColor = setCustomTheme.getSelectedItemTextColor();
        }

        refreshBottomBar();
    }

    public void addItem(BottomBarItem bottomBarItem) {
        bottomBarItems.add(bottomBarItem);
        addingNewItem(bottomBarItem.getTitle(), bottomBarItem.getIcon());
    }

    public void addItem(BottomBarItem.BuiltInItems bottomBarItem) {

        BottomBarItem bottomBarItem1 = new BottomBarItem();

        if (bottomBarItem == BottomBarItem.BuiltInItems.DASHBOARD) {
            bottomBarItem1.setTitle("Dashboard");
            bottomBarItem1.setIcon(R.drawable.dashboard_icon);
        } else if (bottomBarItem == BottomBarItem.BuiltInItems.HOME) {
            bottomBarItem1.setTitle("Home");
            bottomBarItem1.setIcon(R.drawable.home_icon);
        } else if (bottomBarItem == BottomBarItem.BuiltInItems.SEND) {
            bottomBarItem1.setTitle("Send");
            bottomBarItem1.setIcon(R.drawable.send_icon);
        } else if (bottomBarItem == BottomBarItem.BuiltInItems.SETTINGS) {
            bottomBarItem1.setTitle("Settings");
            bottomBarItem1.setIcon(R.drawable.settings_icon);
        } else if (bottomBarItem == BottomBarItem.BuiltInItems.ABOUT_US) {
            bottomBarItem1.setTitle("About Us");
            bottomBarItem1.setIcon(R.drawable.about_us_icon);
        } else if (bottomBarItem == BottomBarItem.BuiltInItems.CONTACT_US) {
            bottomBarItem1.setTitle("Contact Us");
            bottomBarItem1.setIcon(R.drawable.contact_us_icon);
        } else if (bottomBarItem == BottomBarItem.BuiltInItems.DOWNLOAD) {
            bottomBarItem1.setTitle("Downloads");
            bottomBarItem1.setIcon(R.drawable.download_icon);
        } else if (bottomBarItem == BottomBarItem.BuiltInItems.EMAIL) {
            bottomBarItem1.setTitle("Email");
            bottomBarItem1.setIcon(R.drawable.email_icon);
        } else if (bottomBarItem == BottomBarItem.BuiltInItems.FAVOURITES) {
            bottomBarItem1.setTitle("Favourites");
            bottomBarItem1.setIcon(R.drawable.favourite_icon);
        } else if (bottomBarItem == BottomBarItem.BuiltInItems.GALLERY) {
            bottomBarItem1.setTitle("Gallery");
            bottomBarItem1.setIcon(R.drawable.gallery_icon);
        } else if (bottomBarItem == BottomBarItem.BuiltInItems.HELP) {
            bottomBarItem1.setTitle("Help");
            bottomBarItem1.setIcon(R.drawable.help_icon);
        } else if (bottomBarItem == BottomBarItem.BuiltInItems.MESSAGE) {
            bottomBarItem1.setTitle("Message");
            bottomBarItem1.setIcon(R.drawable.message_icon);
        } else if (bottomBarItem == BottomBarItem.BuiltInItems.FEEDBACK) {
            bottomBarItem1.setTitle("Feedback");
            bottomBarItem1.setIcon(R.drawable.feedback_icon);
        } else if (bottomBarItem == BottomBarItem.BuiltInItems.PRIVACY_POLICY) {
            bottomBarItem1.setTitle("Privacy Policy");
            bottomBarItem1.setIcon(R.drawable.privacy_policy_icon);
        } else if (bottomBarItem == BottomBarItem.BuiltInItems.RATE_US) {
            bottomBarItem1.setTitle("Rate Us");
            bottomBarItem1.setIcon(R.drawable.rate_us_icon);
        } else if (bottomBarItem == BottomBarItem.BuiltInItems.UPLOAD) {
            bottomBarItem1.setTitle("Upload");
            bottomBarItem1.setIcon(R.drawable.upload_icon);
        } else if (bottomBarItem == BottomBarItem.BuiltInItems.TOOLS) {
            bottomBarItem1.setTitle("Tools");
            bottomBarItem1.setIcon(R.drawable.tools_icon);
        } else if (bottomBarItem == BottomBarItem.BuiltInItems.SEARCH) {
            bottomBarItem1.setTitle("Search");
            bottomBarItem1.setIcon(R.drawable.search_icon);
        } else if (bottomBarItem == BottomBarItem.BuiltInItems.SHARE) {
            bottomBarItem1.setTitle("Share");
            bottomBarItem1.setIcon(R.drawable.share_icon);
        } else if (bottomBarItem == BottomBarItem.BuiltInItems.TRASH) {
            bottomBarItem1.setTitle("Trash");
            bottomBarItem1.setIcon(R.drawable.trash_icon);
        } else if (bottomBarItem == BottomBarItem.BuiltInItems.PROFILE) {
            bottomBarItem1.setTitle("Profile");
            bottomBarItem1.setIcon(R.drawable.profile_icon);
        }

        bottomBarItems.add(bottomBarItem1);
        addingNewItem(bottomBarItem1.getTitle(), bottomBarItem1.getIcon());
    }

    public void addItem(BottomBarItem.BuiltInItems bottomBarItem, Fragment fragment, int fragmentContainer) {

        BottomBarItem bottomBarItem1 = new BottomBarItem();

        if (bottomBarItem == BottomBarItem.BuiltInItems.DASHBOARD) {
            bottomBarItem1.setTitle("Dashboard");
            bottomBarItem1.setIcon(R.drawable.dashboard_icon);
        } else if (bottomBarItem == BottomBarItem.BuiltInItems.HOME) {
            bottomBarItem1.setTitle("Home");
            bottomBarItem1.setIcon(R.drawable.home_icon);
        } else if (bottomBarItem == BottomBarItem.BuiltInItems.SEND) {
            bottomBarItem1.setTitle("Send");
            bottomBarItem1.setIcon(R.drawable.send_icon);
        } else if (bottomBarItem == BottomBarItem.BuiltInItems.SETTINGS) {
            bottomBarItem1.setTitle("Settings");
            bottomBarItem1.setIcon(R.drawable.settings_icon);
        } else if (bottomBarItem == BottomBarItem.BuiltInItems.ABOUT_US) {
            bottomBarItem1.setTitle("About Us");
            bottomBarItem1.setIcon(R.drawable.about_us_icon);
        } else if (bottomBarItem == BottomBarItem.BuiltInItems.CONTACT_US) {
            bottomBarItem1.setTitle("Contact Us");
            bottomBarItem1.setIcon(R.drawable.contact_us_icon);
        } else if (bottomBarItem == BottomBarItem.BuiltInItems.DOWNLOAD) {
            bottomBarItem1.setTitle("Downloads");
            bottomBarItem1.setIcon(R.drawable.download_icon);
        } else if (bottomBarItem == BottomBarItem.BuiltInItems.EMAIL) {
            bottomBarItem1.setTitle("Email");
            bottomBarItem1.setIcon(R.drawable.email_icon);
        } else if (bottomBarItem == BottomBarItem.BuiltInItems.FAVOURITES) {
            bottomBarItem1.setTitle("Favourites");
            bottomBarItem1.setIcon(R.drawable.favourite_icon);
        } else if (bottomBarItem == BottomBarItem.BuiltInItems.GALLERY) {
            bottomBarItem1.setTitle("Gallery");
            bottomBarItem1.setIcon(R.drawable.gallery_icon);
        } else if (bottomBarItem == BottomBarItem.BuiltInItems.HELP) {
            bottomBarItem1.setTitle("Help");
            bottomBarItem1.setIcon(R.drawable.help_icon);
        } else if (bottomBarItem == BottomBarItem.BuiltInItems.MESSAGE) {
            bottomBarItem1.setTitle("Message");
            bottomBarItem1.setIcon(R.drawable.message_icon);
        } else if (bottomBarItem == BottomBarItem.BuiltInItems.FEEDBACK) {
            bottomBarItem1.setTitle("Feedback");
            bottomBarItem1.setIcon(R.drawable.feedback_icon);
        } else if (bottomBarItem == BottomBarItem.BuiltInItems.PRIVACY_POLICY) {
            bottomBarItem1.setTitle("Privacy Policy");
            bottomBarItem1.setIcon(R.drawable.privacy_policy_icon);
        } else if (bottomBarItem == BottomBarItem.BuiltInItems.RATE_US) {
            bottomBarItem1.setTitle("Rate Us");
            bottomBarItem1.setIcon(R.drawable.rate_us_icon);
        } else if (bottomBarItem == BottomBarItem.BuiltInItems.UPLOAD) {
            bottomBarItem1.setTitle("Upload");
            bottomBarItem1.setIcon(R.drawable.upload_icon);
        } else if (bottomBarItem == BottomBarItem.BuiltInItems.TOOLS) {
            bottomBarItem1.setTitle("Tools");
            bottomBarItem1.setIcon(R.drawable.tools_icon);
        } else if (bottomBarItem == BottomBarItem.BuiltInItems.SEARCH) {
            bottomBarItem1.setTitle("Search");
            bottomBarItem1.setIcon(R.drawable.search_icon);
        } else if (bottomBarItem == BottomBarItem.BuiltInItems.SHARE) {
            bottomBarItem1.setTitle("Share");
            bottomBarItem1.setIcon(R.drawable.share_icon);
        } else if (bottomBarItem == BottomBarItem.BuiltInItems.TRASH) {
            bottomBarItem1.setTitle("Trash");
            bottomBarItem1.setIcon(R.drawable.trash_icon);
        } else if (bottomBarItem == BottomBarItem.BuiltInItems.PROFILE) {
            bottomBarItem1.setTitle("Profile");
            bottomBarItem1.setIcon(R.drawable.profile_icon);
        }
        bottomBarItem1.setFragment(fragment, fragmentContainer);
        bottomBarItems.add(bottomBarItem1);
        addingNewItem(bottomBarItem1.getTitle(), bottomBarItem1.getIcon());
    }

    private void gettingThemeValues() {

        if (bottomBarTheme == BottomBarTheme.LIGHT) {
            iconsColor = Color.parseColor(getColorValue(LearnosetColors.BLACK));
            bottomBarBackgroundColor = Color.parseColor(getColorValue(LearnosetColors.WHITE));
            selectedItemBackgroundColor = Color.parseColor(getColorValue(LearnosetColors.BLUE));
            selectedItemIconColor = Color.parseColor("#FFFFFF");
            selectedItemTextColor = Color.parseColor("#FFFFFF");
            textSize = 16;
        } else if (bottomBarTheme == BottomBarTheme.DARK) {
            iconsColor = Color.parseColor(getColorValue(LearnosetColors.WHITE));
            bottomBarBackgroundColor = Color.parseColor(getColorValue(LearnosetColors.BLACK));
            selectedItemBackgroundColor = Color.parseColor(getColorValue(LearnosetColors.ORANGE));
            selectedItemIconColor = Color.parseColor("#FFFFFF");
            selectedItemTextColor = Color.parseColor("#FFFFFF");
            textSize = 16;
        }
    }

    public void enableAnimation(boolean enableAnimation){
        this.animationEnabled = enableAnimation;
    }

    private void refreshBottomBar() {

        setBackgroundColor(bottomBarBackgroundColor);

        for (int i = 0; i < bottomBarItems.size(); i++) {

            if (i == selectedItem) {
                DrawableCompat.setTint(bottomBarItems.get(i).icon.getDrawable(), selectedItemIconColor);
                bottomBarItems.get(i).titleTextView.setTextColor(selectedItemTextColor);
                bottomBarItems.get(i).titleTextView.setVisibility(View.VISIBLE);
                bottomBarItems.get(i).rootLayout.setBackground(creatingRoundBackground(selectedItemBackgroundColor));

                if (bottomBarItems.get(i).getFragment() != null) {
                    FragmentManager fragmentManager = ((FragmentActivity) context).getSupportFragmentManager();
                    fragmentManager.beginTransaction()
                            .replace(bottomBarItems.get(i).getFragmentContainer(), bottomBarItems.get(i).getFragment(), null)
                            .setReorderingAllowed(true)
                            .addToBackStack(null)
                            .commit();
                }
            } else {
                DrawableCompat.setTint(bottomBarItems.get(i).icon.getDrawable(), iconsColor);
                bottomBarItems.get(i).titleTextView.setVisibility(View.GONE);
                bottomBarItems.get(i).rootLayout.setBackgroundColor(bottomBarBackgroundColor);
            }

        }
    }

    private LinearLayout createNewRoot() {

        LinearLayout rootLayout = new LinearLayout(context);

        LayoutParams rootLayoutParams = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.MATCH_PARENT);
        rootLayoutParams.weight = 1;
        rootLayout.setLayoutParams(rootLayoutParams);
        rootLayout.setPadding(convertDpToPixel(5), 0, convertDpToPixel(5), 0);
        rootLayout.setGravity(Gravity.CENTER);
        rootLayout.setOnClickListener(this);
        rootLayout.setTag((bottomBarItems.size() - 1));

        return rootLayout;
    }

    private ImageView createNewIcon(int src) {

        LayoutParams imageLayoutParams = new LayoutParams(convertDpToPixel(20), ViewGroup.LayoutParams.WRAP_CONTENT);
        ImageView imageView = new ImageView(context);
        imageView.setLayoutParams(imageLayoutParams);
        imageView.setAdjustViewBounds(true);
        imageView.setImageResource(src);

        return imageView;
    }

    private TextView createNewText(String text) {

        TextView textView = new TextView(context);

        LayoutParams textViewLayoutParams = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        textViewLayoutParams.setMarginStart(20);
        textView.setLayoutParams(textViewLayoutParams);
        textView.setGravity(Gravity.CENTER);
        textView.setTextSize(textSize);
        textView.setText(text);
        textView.setMaxLines(1);
        textView.setTypeface(Typeface.DEFAULT_BOLD);

        return textView;
    }

    public void removeShadow() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            setElevation(0);
        }
    }

    private void addingNewItem(String title, int src) {

        setWeightSum(bottomBarItems.size());

        LinearLayout rootLayout = createNewRoot();
        ImageView icon = createNewIcon(src);
        TextView text = createNewText(title);

        rootLayout.addView(icon);
        rootLayout.addView(text);

        addView(rootLayout);

        BottomBarItem bottomBarItem = bottomBarItems.get(bottomBarItems.size() - 1);
        bottomBarItem.rootLayout = rootLayout;
        bottomBarItem.icon = icon;
        bottomBarItem.titleTextView = text;

        refreshBottomBar();
    }

    private Drawable creatingRoundBackground(int backColor) {

        GradientDrawable drawable = new GradientDrawable();
        drawable.setCornerRadius(100);
        drawable.setShape(GradientDrawable.RECTANGLE);
        drawable.setColor(backColor);

        return drawable;
    }

    private int convertPixelsToDp(float px) {
        return (int) (px / ((float) context.getResources().getDisplayMetrics().densityDpi / DisplayMetrics.DENSITY_DEFAULT));
    }

    private int convertDpToPixel(float dp) {
        return (int) (dp * ((float) context.getResources().getDisplayMetrics().densityDpi / DisplayMetrics.DENSITY_DEFAULT));
    }

    private String getColorValue(LearnosetColors navColor) {

        String selectedColorValue = "";

        if (navColor == LearnosetColors.RED) {
            selectedColorValue = "#FFFF1744";
        } else if (navColor == LearnosetColors.BLACK) {
            selectedColorValue = "#000000";
        } else if (navColor == LearnosetColors.GRAY) {
            selectedColorValue = "#998A8A8A";
        } else if (navColor == LearnosetColors.ORANGE) {
            selectedColorValue = "#FF9100";
        } else if (navColor == LearnosetColors.WHITE) {
            selectedColorValue = "#FFFFFF";
        } else if (navColor == LearnosetColors.YELLOW) {
            selectedColorValue = "#FFEA00";
        } else if (navColor == LearnosetColors.DARK_RED) {
            selectedColorValue = "#FFD50000";
        } else if (navColor == LearnosetColors.LIGHT_RED) {
            selectedColorValue = "#FFFF8A80";
        } else if (navColor == LearnosetColors.DARK_ORANGE) {
            selectedColorValue = "#FFFF6D00";
        } else if (navColor == LearnosetColors.LIGHT_ORANGE) {
            selectedColorValue = "#FFFFD180";
        } else if (navColor == LearnosetColors.BLUE) {
            selectedColorValue = "#FF00B0FF";
        } else if (navColor == LearnosetColors.DARK_BLUE) {
            selectedColorValue = "#FF0091EA";
        } else if (navColor == LearnosetColors.LIGHT_BLUE) {
            selectedColorValue = "#FF80D8FF";
        }

        return selectedColorValue;
    }

    @Override
    public void onClick(View v) {

        LinearLayout rootLayout = (LinearLayout) v;
        selectedItem = (int) rootLayout.getTag();

        refreshBottomBar();

        TextView getTextView = bottomBarItems.get(selectedItem).titleTextView;
        ImageView icon = bottomBarItems.get(selectedItem).icon;

        int getTextViewWidth = (int) ((getWidth() - icon.getWidth() - rootLayout.getPaddingRight() - rootLayout.getPaddingLeft() - convertDpToPixel(20)) / getWeightSum());
        if (getTextView.getText().length() < 5) {
            getTextViewWidth = getTextViewWidth - 80;
        }

        if (getTextViewWidth > 10 && animationEnabled) {
            ValueAnimator animation = ValueAnimator.ofInt(0, getTextViewWidth);
            animation.setDuration(200);
            animation.start();

            animation.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                @Override
                public void onAnimationUpdate(ValueAnimator animation) {
                    getTextView.setWidth((Integer) animation.getAnimatedValue());
                }
            });
        }

        if (bottomBarItems.get(selectedItem).getFragment() != null) {
            FragmentManager fragmentManager = ((FragmentActivity) context).getSupportFragmentManager();
            fragmentManager.beginTransaction()
                    .replace(bottomBarItems.get(selectedItem).getFragmentContainer(), bottomBarItems.get(selectedItem).getFragment(), null)
                    .setReorderingAllowed(true)
                    .addToBackStack(null)
                    .commit();
        }

        if (bottomBarEventListener != null) {
            bottomBarEventListener.onItemSelected(bottomBarItems.get(selectedItem), selectedItem);
        }

    }

    public enum BottomBarTheme {
        LIGHT,
        DARK
    }

    public enum LearnosetColors {
        DEFAULT,
        GRAY,
        RED,
        DARK_RED,
        LIGHT_RED,
        BLACK,
        WHITE,
        ORANGE,
        DARK_ORANGE,
        LIGHT_ORANGE,
        YELLOW,
        BLUE,
        LIGHT_BLUE,
        DARK_BLUE
    }
}
