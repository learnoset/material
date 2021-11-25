
# Best Material UI design Library for Android Studio

Learnoset Material UI is a powerful library for Android Studio that consist of
<ul>
<a href = "#navigation_bar"><li>Custom Navigation Drawer</li></a>
<li>Material Bottom Bar</li>
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
<h3 id = "navigation_bar">How to use Custom Navigation Drawer in Android Studio</h3>
1. Add below line in your module level build.gradle file

```groovy
implementation 'com.github.learnoset:material.ui:2.26'
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


3. Add below code in the XML file

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

4. Using Built-In Items in the Navigation Bar

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

5. Using your own custom Item Names and Icons

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

6. Creating Group of Items

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

7. Using Navigation Themes
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
customNavTheme.setTextColor(Color.WHITE);

learnosetNavigationBar.setTheme(customNavTheme);

```

<br>

8. Add Event Listener
```java
learnosetNavigationBar.setEventListener(new NavigationEventListener() {
    @Override
    public void onItemSelected(int position, LearnosetNavItem selectedNavItem) {
        // TODO Your code goes here to perform action according to the selected Item
    }
});
```

<br>

9. Change Selected Item Background Color
```java
learnosetNavigationBar.setSelectedItemBackground(LearnosetNavigationBar.NavColors.ORANGE);
```

<br>

10. Change Icons Color
```java
learnosetNavigationBar.setIconsColor(LearnosetNavigationBar.NavColors.RED);
```

<br>

11. Setting Header Data (Profile Name & Profile Picture)
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

12. Hide Log Out Button
```java
learnosetNavigationBar.enableLogOutBtn(false);
```

<br>

You can Visit our Website to learn more about Android App Development<br>
[Learnoset Website](https://learnoset.com/)

<br>
We provide source code for Login & Register pages, Custom Dialogs, Custom Navigation Bar, Custom Toolbar, Custom Bottom Bar with material UI design and complete project files
