package com.example.zdk.paopao1.activity;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.zdk.paopao1.R;

public class ImageShowActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_show);
        ImageView imageView=findViewById(R.id.image_show);
        Intent intent = getIntent();
        String imageurl = intent.getStringExtra("imageurl");

        Glide.with(this).load( imageurl).into(imageView);

        // 两秒后关闭后dialog
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
               finish();
            }
        }, 1000 * 3);
    }
}
