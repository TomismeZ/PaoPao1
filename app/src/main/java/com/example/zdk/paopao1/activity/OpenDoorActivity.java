package com.example.zdk.paopao1.activity;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.AsyncTask;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import com.example.zdk.paopao1.R;

import java.net.MalformedURLException;
import java.net.URL;

public class OpenDoorActivity extends AppCompatActivity {
    public static final String PREFERENCE_PACKAGE = "com.myncic.ican";
    public static final String PREFERENCE_NAME = "userInfo";
    public static int MODE =Context.MODE_WORLD_WRITEABLE;
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
                String securitycode=preferences.getString("security", "");
                //获取用户登录信息
                Long selfId=preferences.getLong("selfId", -1);
                int port=preferences.getInt("port",1234);
                Log.d("OpenDoorActivity","ipAdd: "+ipAdd);
                Log.d("OpenDoorActivity","port: "+port);
                Log.d("OpenDoorActivity","selfId: "+selfId);
                Log.d("OpenDoorActivity","securitycode: "+securitycode);
//                Check check=new Check(selfId,securitycode);
//                check.execute();
            }
        });
    }

    class Check extends AsyncTask<Integer,Integer,String>{
        Long selfId;
        String securitycode;

        public Check(Long selfId, String securitycode) {
            this.selfId = selfId;
            this.securitycode = securitycode;
        }

        @Override
        protected String doInBackground(Integer... integers) {
            try {
                URL url=new URL("http://oa.myncic.com/open_door.php?uid="+selfId+"&scode="+securitycode);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }
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
