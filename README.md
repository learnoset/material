
# Best Material UI design Library for Android Studio

![](https://learnoset.com/GitHubImages/custom-navigation-drawer-in-android-studio.png)


Learnoset Material UI is a powerful library for Android Studio that consist of
<ul>
<a href = "#navigation_bar"><li>Custom Navigation Drawer</li></a>
 <a href = "#bottom_bar"><li>Material Bottom Bar</li></a>
<li>Material Custom Dialogs</li>
<li>Material Progress Bars</li>
<li>Material Tab Layout</li>
<li>Material Toast Messages</li>
<li>Custom Designed Buttons</li>
<li>Custom Designed EditText</li>
</ul>
<br>

<h3>Why use Learnoset Material UI Library</h3>
<ul>
<li>
  Easy to Use 
 </li>
  <li>
  Don't need to right lengthy code
 </li>  
  <li>
  Easy to implement
 </li>
  <li>
  Responsive Designs
 </li>
  <li>
  Modern Designs
 </li> 
</ul>
<br><br>
<h3>How to add Learnoset Material UI library to your Project?</h3>
1. Add below line in your module level build.gradle file

```groovy
implementation 'com.github.learnoset:material:3.38'
```

<br>

2. Add below line in your project level build.gradle file

```groovy
allprojects {
    repositories {
        google()
        jcenter()
        
        // add below line
        maven {url 'https://jitpack.io' }
    }
}
```

<br>

<h3 id = "navigation_bar">How to use Custom Navigation Drawer in Android Studio</h3>

<br>

1. Add below code in the XML file

```xml
<?xml version="1.0" encoding="utf-8"?>
<androidx.drawerlayout.widget.DrawerLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:tools="http://schemas.android.com/tools"
    android:id="@+id/drawerLayout"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    tools:context=".MainActivity">


    <LinearLayout
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        android:gravity="center">

        <androidx.appcompat.widget.AppCompatButton
            android:id="@+id/openNavigation"
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:text="Open Navigation" />
    </LinearLayout>

    <com.learnoset.material.ui.learnosetnavigationbar.LearnosetNavigationBar
        android:id="@+id/navigatioNabr"
        android:layout_width="wrap_content"
        android:layout_height="match_parent"
        android:layout_gravity="start" />
</androidx.drawerlayout.widget.DrawerLayout>
```

<br>

2. Using Built-In Items in the Navigation Bar

```java
final DrawerLayout drawerLayout = findViewById(R.id.drawerLayout);
final Button openNavigationBtn = findViewById(R.id.openNavigation);
final LearnosetNavigationBar learnosetNavigationBar = findViewById(R.id.navigatioNabr);

// You can use some Built-In Navigation Items
learnosetNavigationBar.addNavItem(LearnosetNavItem.BuiltInItems.HOME);
learnosetNavigationBar.addNavItem(LearnosetNavItem.BuiltInItems.DASHBOARD);
learnosetNavigationBar.addNavItem(LearnosetNavItem.BuiltInItems.SETTINGS);

// setting Drawer Layout with Drawer Gravity. If LEFT then Navigation Bar opens from Left Side. If RIGHT then opens from right Side
learnosetNavigationBar.setDrawerLayout(drawerLayout, LearnosetNavigationBar.DrawerGravity.LEFT);
        
// open drawer on button click
openNavigationBtn.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        drawerLayout.openDrawer(GravityCompat.START);
    }
});
```

<br>

3. Using your own custom Item Names and Icons

```java
// You can use Custom Items
LearnosetNavItem customItem1 = new LearnosetNavItem();
customItem1.setTitle("Custom Title");
customItem1.setIcon(R.drawable.custom_icon);

LearnosetNavItem customItem2 = new LearnosetNavItem("Custom Item 2", R.drawable.custom_item_2);

// adding custom items to Learnoset Navigation Bar
learnosetNavigationBar.addNavItem(customItem1);
learnosetNavigationBar.addNavItem(customItem2);
```

<br>

4. Creating Group of Items

```java
// CREATING GROUP OF ITEMS
NavItemsGroup navItemsGroup = new NavItemsGroup("Group Name");

// adding Built-In Items to the group
navItemsGroup.addGroupItem(LearnosetNavItem.BuiltInItems.HOME);
navItemsGroup.addGroupItem(LearnosetNavItem.BuiltInItems.SEARCH);

// adding custom Items to the group
navItemsGroup.addGroupItem(new LearnosetNavItem("Custom Group Item", R.drawable.custom_item_icon));

LearnosetNavItem customGroupItem2 = new LearnosetNavItem("Custom Group Item 3 ", R.drawable.custom_item_3);
navItemsGroup.addGroupItem(customGroupItem2);

// adding group to the Navigation Bar
learnosetNavigationBar.addItemsGroup(navItemsGroup);
```

<br>

5. Using Navigation Themes
```java
learnosetNavigationBar.setTheme(LearnosetNavigationBar.NavThemes.DARK);
// OR
learnosetNavigationBar.setTheme(LearnosetNavigationBar.NavThemes.LIGHT);
// OR
// Setting Custom Theme
CustomNavTheme customNavTheme = new CustomNavTheme();
customNavTheme.setIconsColor(Color.parseColor("#FF03DAC5"));
customNavTheme.setNavigationBackground(Color.BLACK);
customNavTheme.setSelectedItemBackgroundColor(Color.parseColor("#FF03DAC5"));
customNavTheme.setSelectedItemIconColor(Color.ORANGE);
customNavTheme.setSelectedItemTextColor(Color.YELLOW);
customNavTheme.setTextColor(Color.WHITE);

learnosetNavigationBar.setTheme(customNavTheme);

```

<br>

6. Add Event Listener
```java
learnosetNavigationBar.setEventListener(new NavigationEventListener() {
    @Override
    public void onItemSelected(int position, LearnosetNavItem selectedNavItem) {
        // TODO Your code goes here to perform action according to the selected Item
    }
});
```

<br>

7. Change Selected Item Background Color
```java
learnosetNavigationBar.setSelectedItemBackground(LearnosetNavigationBar.NavColors.ORANGE);
```

<br>

8. Change Icons Color
```java
learnosetNavigationBar.setIconsColor(LearnosetNavigationBar.NavColors.RED);
```

<br>

9. Setting Header Data (Profile Name & Profile Picture)
```java
// setting header profile name and profile image (from resources)
learnosetNavigationBar.setHeaderData("John Corner", R.drawable.profile_image);

// setting header profile name and profile image (from File)
File file = new File("You Image File Path");
try {
    learnosetNavigationBar.setHeaderData("John Corner", file);
} catch (LearnosetExceptions learnosetExceptions) {
    learnosetExceptions.printStackTrace();
}

// setting header profile name and profile image (from URL)
learnosetNavigationBar.setHeaderData("John Corner", "url of image");
```

<br>

10. Hide Log Out Button
```java
learnosetNavigationBar.enableLogOutBtn(false);
```

<br>

<h3 id = "bottom_bar">How to use Custom Bottom Bar in Android Studio</h3>

<br>

1. Add below code in your XML file
```xml
<com.learnoset.material.ui.learnosetbottombar.LearnosetBottomBar
        android:id="@+id/bottomBar"
        android:layout_width="match_parent"
        android:layout_height="70dp"
        android:layout_alignParentBottom="true" />
```

<br>

2. Add below code in your Activity.class file
```java
final LearnosetBottomBar bottomBar = findViewById(R.id.bottomBar);

// adding Items to the bottom bar (You can also use Built-In Items and Icons)
bottomBar.addItem(BottomBarItem.BuiltInItems.HOME);
bottomBar.addItem(BottomBarItem.BuiltInItems.MESSAGE);
bottomBar.addItem(BottomBarItem.BuiltInItems.SETTINGS);
bottomBar.addItem(BottomBarItem.BuiltInItems.PROFILE);

// OR you can also add custom Item with your own Text and Icon
bottomBar.addItem(new BottomBarItem("Custom Item 1", R.drawable.custom_icon));
```

<br>

3. Using themes with Bottom Bar
```java
bottomBar.setTheme(LearnosetBottomBar.BottomBarTheme.LIGHT);
// OR
bottomBar.setTheme(LearnosetBottomBar.BottomBarTheme.DARK);
// OR
CustomBottomBarTheme customBottomBarTheme = new CustomBottomBarTheme(); // creating custom theme object
customBottomBarTheme.setBackgroundColor(Color.BLACK); // set background color for bottom bar
customBottomBarTheme.setSelectedItemBackgroundColor(LearnosetBottomBar.LearnosetColors.ORANGE); // setting selected item background color
customBottomBarTheme.setIconsColor(LearnosetBottomBar.LearnosetColors.ORANGE); // setting icons color
customBottomBarTheme.setSelectedItemTextColor(Color.WHITE); // setting text color

bottomBar.setTheme(customBottomBarTheme); // setting custom theme to the bottom bar
// new bottom bar animations are coming soon.
```

<br>

4. Bottom Bar Animation
```java
// enable animation
bottomBar.enableAnimation(true);
// OR
// disable animation
bottomBar.enableAnimation(false);

// new bottom bar animations are coming soon.
```

You can Visit our Website to learn more about Android App Development, Java, Python, JavaScript, Artificial Intelligence<br>
[Learnoset Website](https://learnoset.com/)

<br>
We provide source code for Login & Register pages, Custom Dialogs, Custom Navigation Bar, Custom Toolbar, Custom Bottom Bar with material UI design and complete project files
