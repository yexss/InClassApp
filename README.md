# InClassApp

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
