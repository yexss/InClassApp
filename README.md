# InClassApp


# Design Meta Work

## 完善DashboardFragment界面的TabLs的背景色与colorPrimary一致

<ol>
    <li>在theme.xml文件里面写好TabLs样式</li>
    <li>DashboardFragment.xml文件里面的TabLayout链接上Theme.TabLs</li>
</ol>

```xml
    <item name="tabBackground">@color/purple_500</item>
    <item name="tabIndicatorColor">#3D87FB</item>
    <item name="tabIndicatorHeight">2dp</item>
    <item name="tabTextAppearance">@style/TextAppearance.Design.Tab</item>
    <item name="tabSelectedTextColor">#FFFFFF</item>
```

```xml
    <com.google.android.material.tabs.TabLayout
        android:id="@+id/tabs_dashboard"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:theme="@style/Theme.InClassApp"/>
```

## 切换页面时，Toolbar显示底部导航的标题
主activity方法里面NavigationBarView监听器换页面就重设toolbar标题
```java
private final NavigationBarView.OnItemSelectedListener navListener = new NavigationBarView.OnItemSelectedListener() {
    @SuppressLint("NonConstantResourceId")
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.navgation_home:
                binding.viewPager.setCurrentItem(0);
                binding.toolbar.setTitle(R.string.title_home);
                return true;
            case R.id.navgation_dashboard:
                binding.viewPager.setCurrentItem(1);
                binding.toolbar.setTitle(R.string.title_dashboard);
                return true;
            case R.id.navgation_notification:
                binding.viewPager.setCurrentItem(2);
                binding.toolbar.setTitle(R.string.title_notification;
                return true;
        }
        return false;
    }
};
```

## 给Toolbar添加菜单，完成菜单项选择后的Snackbar提示

<ol>
    <li>menu文件夹里面新建一个design_meta_menu.xml文件</li>
    <li>主activity方法里面写构造器与监听器</li>
</ol>

构造器
```java
@Override
public boolean onCreateOptionsMenu(Menu menu) {
    MenuInflater inflater = getMenuInflater();
    inflater.inflate(R.menu.meta_design_menu,menu);
    return true;
}
```
监听器
```java
@Override
public boolean onOptionsItemSelected(@NonNull MenuItem item) {
    if(item.getItemId()==R.id.popup){
        showSnackBarMsg("pop option menu clicked");
        return true;
    }
    return super.onOptionsItemSelected(item);
}
```

<br>





# 菜单

## 使用xml定义菜单

<ul>
    <li>menu : 菜单根节点，能够包含一个或多个 item 和 group元素，它是定义菜单项的容器</li>
    <li>item : 菜单项节点，用于创建 MenuItem 对象，可包含嵌套 menu 元素，以便创建子菜单</li>
    <li>group : 用于对菜单项进行分类，它是 item 元素的不可见容器</li>
</ul>

<br>

### xml 菜单示例如下
```xml
<item 
    android:id="@+id/clear_height"
    android:title="@string/clear_height"
    android:icon="@drawable/ic_baseline_accessible_forward_24"
    android:iconTint="@color/black"
    app:showAsAction="withText"/>
```
<ul>
    <li>android:id : 设置菜单项id</li>
    <li>android:id : 设置菜单项的图标</li>
    <li>android:id : 设置菜单项的标题字符串</li>
    <li>android:id : 指定菜单项在标题栏的显示的时机和方式</li>
</ul>

<br>

### Activity方法加载、创建菜单
```java
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // 加载xml菜单资源
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.bmimenu, menu);
        // 动态加载菜单项
        menu.add(Menu.NONE, R.id.clear_weight, 1, "清楚体重");
        setIconsVisible(menu);
        return true;
    }
```

```java
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        // 处理菜单项的选择
        switch (item.getItemId()) {
            case R.id.clear_weight:
                binding.weight.setText("");
                return true;
            case R.id.clear_height:
                binding.height.setText("");
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
```
