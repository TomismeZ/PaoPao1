package com.example.zdk.paopao1.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.zdk.paopao1.R;
import com.example.zdk.paopao1.fragment.ApplicationFragment;
import com.example.zdk.paopao1.fragment.ChatFragment;
import com.example.zdk.paopao1.fragment.DepartmentFragment;
import com.example.zdk.paopao1.fragment.HomeFragment;

public class MainActivity extends AppCompatActivity {
    private DrawerLayout drawerLayout;
    RadioGroup group;
    HomeFragment homeFragment;
    ChatFragment chatFragment;
    DepartmentFragment departmentFragment;
    ApplicationFragment applicationFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView(); //初始化组件

    }

    /**
     * 初始化组件及注册监听器
     */
    private void initView() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);
        //制作默认首页
        homeFragment = new HomeFragment();
        this.getSupportFragmentManager().beginTransaction().add(R.id.container, homeFragment).commit();
        ActionBar actionBar = getSupportActionBar();
        //让导航按钮显示出来
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeAsUpIndicator(R.mipmap.ic_menu);
        }
        //侧滑菜单
        drawerLayout = findViewById(R.id.drawer_layout);
        //导航栏
        NavigationView navView = findViewById(R.id.nav_view);
        //NavigationView的点击事件
        navView.setCheckedItem(R.id.nav_Call);
        navView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                drawerLayout.closeDrawers();
                return true;
            }
        });

        group = findViewById(R.id.radiobutton_main);
//        给radioGroup添加点击事件
        group.setOnCheckedChangeListener(new MyRadioListener());
    }

    class MyRadioListener implements RadioGroup.OnCheckedChangeListener {

        @Override
        public void onCheckedChanged(RadioGroup group, int checkedId) {
            //一个 事务只能提交一次
            //获取一个碎片管理器
            FragmentManager fragmentManager = MainActivity.this.getSupportFragmentManager();
            //启动事务  交易
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            //			fragmentTransaction.add(arg0, arg1);
//			fragmentTransaction.remove(arg0);
//			fragmentTransaction.show(arg0);
//			fragmentTransaction.hide(arg0);
//			fragmentTransaction.replace(arg0, arg1);//替换  fragment中的内容每次会重新加载
            //把四个碎片都隐藏起来
            if (homeFragment != null) {
                fragmentTransaction.hide(homeFragment);
            }
            if (chatFragment != null) {
                fragmentTransaction.hide(chatFragment);
            }
            if (departmentFragment != null) {
                fragmentTransaction.hide(departmentFragment);
            }
            if (applicationFragment != null) {
                fragmentTransaction.hide(applicationFragment);
            }
            // 通过switch区分四个radiobutton

            switch (checkedId) {
                case R.id.rb_home:
                    //显示出对应的界面  fragment
                    if (homeFragment==null) {
                        //实例化一个 fragment
                        homeFragment=new HomeFragment();
                        fragmentTransaction.add(R.id.container, homeFragment);
                    }else {
                        fragmentTransaction.show(homeFragment);
                    }
                    break;
                case R.id.rb_chat:
                    //显示出对应的界面  fragment
                    if (chatFragment==null) {
                        //实例化一个 fragment
                        chatFragment=new ChatFragment();
                        fragmentTransaction.add(R.id.container, chatFragment);
                    }else {
                        fragmentTransaction.show(chatFragment);
                    }
                    break;
                case R.id.rb_department:
                    //显示出对应的界面  fragment

                    if (departmentFragment==null) {
                        //实例化一个 fragment
                        departmentFragment=new DepartmentFragment();
                        fragmentTransaction.add(R.id.container, departmentFragment);

                    }else {
                        fragmentTransaction.show(departmentFragment);
                    }
                    break;
                case R.id.rb_application:
                    //显示出对应的界面  fragment

                    if (applicationFragment==null) {
                        //实例化一个 fragment
                        applicationFragment=new ApplicationFragment();
                        fragmentTransaction.add(R.id.container, applicationFragment);

                    }else {
                        fragmentTransaction.show(applicationFragment);
                    }
                    break;
                default:
                    break;
            }

            fragmentTransaction.commit(); //提交，否则没有任何效果

        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.settings:
                Toast.makeText(this, "You clicked Settings", Toast.LENGTH_SHORT).show();
                break;
            case android.R.id.home:
                drawerLayout.openDrawer(GravityCompat.START);
                break;
            default:
                break;
        }
        return true;
    }
}
