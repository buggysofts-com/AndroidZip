# BottomSheetMenu [![Release](https://jitpack.io/v/buggysofts-com/BottomSheetMenu.svg)](https://jitpack.io/#buggysofts-com/BottomSheetMenu)

A powerful &amp; customizable menu implementation for android. It supports any level of nested menu structures along with custom header and footer views, and much more. Follow the steps below to import the library to your project. You will also find some sample codes.

<br />

## Import
Add JitPack repository to your project level build.gradle file
```
...

allprojects {
    repositories {
        ...
        maven { url 'https://jitpack.io' }
    }
}
```
Or, in newer android projects, if you need to the add repository in settings.gradle file...
```
...

dependencyResolutionManagement {
    ...
    repositories {
        ...
        maven { url 'https://jitpack.io' }
    }
}
```
Finally, add this dependency to your app/module level build.gradle file
```
...

dependencies {
    ...
    implementation 'com.github.buggysofts-com:BottomSheetMenu:v1.0.4'
}
```
And you are done importing the library.

<br />

## Sample codes

To create a minimal bottom sheet menu...
```
BottomSheetMenu bottomSheetMenu = new BottomSheetMenu(
  MainActivity.this,
  R.menu.sample_menu,
  new BottomSheetMenu.MenuItemClickListener() {
      @Override
      public void onClick(MenuItem item) {
          Toast.makeText(MainActivity.this, item.getTitle(), Toast.LENGTH_SHORT).show();
      }
  }
).show();
```
<br />

You can use methods that follow builder pattern to set properties of different components of the menu. For example the following code snippet sets background, divider, menu icon place holder (in case a menu item does not have an icon), expand icon (for indicating submenu of a menu item), a title and finally it shows the menu. There are other constructors where you can set these properties all at once.
```
bottomSheetMenu
  .menuBackground(
      AppCompatResources.getDrawable(
        MainActivity.this,
        R.drawable.menu_bg
      )
  )
  .dividerDrawable(
      BottomSheetMenu.getSystemDefaultDivider(
          MainActivity.this
      )
  )
  .menuIconPlaceHolder(
      AppCompatResources.getDrawable(
          MainActivity.this,
          R.drawable.ic_menu_item
      )
  )
  .menuExpandIcon(
      AppCompatResources.getDrawable(
          MainActivity.this,
         R.drawable.ic_arrow_forward
      )
  )
  .menuTitle("Property Setting Demo")
  .show();
```
You can obtain various default properties with the static methods available in the BottomSheetMenu class, for example ```BottomSheetMenu.getDefaultExpandIcon(context)``` returns the default drawable used as the expand icon. There are other methods for other properties as well. But you don't need to explicitly call these methods, these properties are set by default.

<br />

The menu supports custom header and footer views. You can select different header and footer views for different menu items, and also for initial call to ```show()```. There may be many use cases for different header and footer views, such as description of the the submenu of a menu item can be placed as the footer view. See the code snippet below to know how you can define these selectors.
```
bottomSheetMenu
    .headerViewSelector(
        new BottomSheetMenu.ViewSelector() {
            @Nullable
            @Override
            public View getInitialView() {
                return getLayoutInflater().inflate(R.layout.initial_menu_header, null);
            }

            @Nullable
            @Override
            public View selectViewForItem(MenuItem item) {
                int itemId = item.getItemId();
                if (itemId == R.id.menu_image) {
                    return getLayoutInflater().inflate(R.layout.image_info_layout, null);
                } else if (itemId == R.id.menu_video) {
                    return getLayoutInflater().inflate(R.layout.video_info_layout, null);
                } else {
                    return getLayoutInflater().inflate(R.layout.raw_file_info_layout, null);
                }
            }
        }
    )
    .menuTitle("View Selecting Demo")
    .show();
```
The above code selects different views for different selected menu item(having submenu). Also, it selects an initial header view. Similarly, you can set footer views with ```footerViewSelector(...)``` method.

<br />

## Images
Here are some example images of the menu containing a header and a footer view. The one above the top divider line is the header view, and the one below the bottom divider line is the footer view. You can select your desider header or footer views, or customize the menu styles and behaviours by following the topics above. Note, the styling applied to the menu does not apply on header and footer views, they are external views and you should apply necessary styling to them before using them in the menu.

Root menu:
![Root menu](/app/src/main/res/drawable/root_menu.png)

Nested submenu:
![Nested submenu](/app/src/main/res/drawable/menu_level_2.png)


<br />
<br />

Please share & rate the library if you find it useful.

### Happy coding!