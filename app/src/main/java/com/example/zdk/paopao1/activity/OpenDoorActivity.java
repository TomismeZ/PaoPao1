package com.example.zdk.paopao1.activity;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import com.example.zdk.paopao1.R;

public class OpenDoorActivity extends AppCompatActivity {
    public static final String PREFERENCE_PACKAGE = "edu.cczu.SimplePreference";
    public static final String PREFERENCE_NAME = "userInfo";
    public static int MODE = Context.MODE_WORLD_READABLE + Context.MODE_WORLD_WRITEABLE;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_open_door);
        //标题栏
        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        //让导航按钮显示出来
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
//            actionBar.setHomeAsUpIndicator(R.mipmap.back_n);
        }

        initView();
    }

    private void initView() {
        ImageView imageView=findViewById(R.id.iv_open_door);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Context c=null;
                try {
                    c= OpenDoorActivity.this.createPackageContext(PREFERENCE_PACKAGE,Context.CONTEXT_IGNORE_SECURITY);
                } catch (PackageManager.NameNotFoundException e) {
                    e.printStackTrace();
                }
                SharedPreferences preferences=c.getSharedPreferences(PREFERENCE_NAME,MODE);
                String ipAdd=preferences.getString("ipAdd","oa.myncic.com");
                int port=preferences.getInt("port",1234);
                Log.d("OpenDoorActivity","ipAdd: "+ipAdd);
                Log.d("OpenDoorActivity","port: "+port);

            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                finish();
                break;
        }
        return true;
    }
}
