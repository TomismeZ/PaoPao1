package com.example.zdk.paopao1.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.zdk.paopao1.R;
import com.example.zdk.paopao1.adapter.ImagesAdapter;
import com.example.zdk.paopao1.bean.ImageInfo;
import com.example.zdk.paopao1.util.HttpCallbackListener;
import com.example.zdk.paopao1.util.HttpUtil;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/12/29 0029.
 */

public class ImageFragment extends Fragment {

    private List<ImageInfo> imageInfos;
    private SwipeRefreshLayout swipeRefreshLayout;
    private ImagesAdapter imagesAdapter;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_image,null);
        initView(view); //初始化组件
        return view;
    }

    /**
     * 初始化组件与事件
     *
     * @param view
     */
    private void initView(View view) {
        swipeRefreshLayout = view.findViewById(R.id.swipe_refresh);
        swipeRefreshLayout.setColorSchemeResources(R.color.colorPrimary);
        imageInfos = new ArrayList<>();

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                requestData();
            }
        });
        requestData(); //初始化数据

        RecyclerView recyclerView = view.findViewById(R.id.recycler_view);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        imagesAdapter = new ImagesAdapter(imageInfos);
        recyclerView.setAdapter(imagesAdapter);


        //悬浮按钮点击事件
        FloatingActionButton fab = view.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Toast.makeText(MainActivity.this,"FAB clicked",Toast.LENGTH_SHORT).show();
                Snackbar.make(view, "Data commit", Snackbar.LENGTH_SHORT)
                        .setAction("Undo", new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                Toast.makeText(getActivity(), "commit success", Toast.LENGTH_SHORT).show();
                            }
                        })
                        .show();
            }
        });

        //        ListView lv = findViewById(R.id.lv);
//        ImageAdapter adapter = new ImageAdapter(imageInfos, this);
//        lv.setAdapter(adapter);


    }

    /**
     * 请求数据
     */
    private void requestData() {
//        imageInfos.clear();

        HttpUtil.sendHttpRequest("http://paopao.myncic.com/plugin_1?c=api&a=quanTopicList&clientapp=android&tid=0&area=%E9%81%B5%E4%B9%89%E5%B8%82", new HttpCallbackListener() {
            @Override
            public void onFinish(final String response) {
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Log.d("MainActivity", response);

//                Gson gson = new Gson();
//
//                Image image = gson.fromJson(response, Image.class);
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            String temData = jsonObject.getString("data");
                            Log.d("MainActivity", temData);
                            JSONArray jsonArray = new JSONArray(temData);
                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject jsonObject1 = jsonArray.getJSONObject(i);
                                ImageInfo imageInfo = new ImageInfo();
                                imageInfo.setAvatar(jsonObject1.getString("avatar"));
                                Log.d("MainActivity", imageInfo.getAvatar());
                                imageInfo.setUsername(jsonObject1.getString("nickname"));
                                Log.d("MainActivity", "nickname: " + imageInfo.getUsername());
                                imageInfo.setContent(jsonObject1.getString("txt"));
                                Log.d("MainActivity", "txt: " + imageInfo.getContent());

                                imageInfo.setPublishTime(jsonObject1.getString("showdate"));
                                imageInfo.setAgree(jsonObject1.getInt("agree"));
                                Log.d("MainActivity", "agree: " + imageInfo.getAgree());
                                imageInfo.setComment(jsonObject1.getInt("comment"));
                                Log.d("MainActivity", "comment: " + imageInfo.getComment());

                                String ext = jsonObject1.getString("ext");
                                JSONObject jsonObject2 = new JSONObject(ext);

                                JSONArray jsonArray1 = new JSONArray(jsonObject2.getString("image"));
                                JSONObject jsonObject3 = jsonArray1.getJSONObject(0);
                                List<String> imageUrls = new ArrayList<>();
                                //图片内容
                                for (int j = 0; j < jsonArray1.length(); j++) {
                                    JSONObject jsonObject4 = jsonArray1.getJSONObject(j);
                                    String imageUrl = jsonObject4.getString("url");
                                    imageUrls.add(imageUrl);
                                }

                                imageInfo.setImageUrls(imageUrls);

                                imageInfo.setImageUrl(jsonObject3.getString("url"));
                                imageInfos.add(imageInfo);

                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                        swipeRefreshLayout.setRefreshing(false);
                    }
                });


            }

            @Override
            public void onError(Exception e) {
                swipeRefreshLayout.setRefreshing(false);
            }
        });
    }
}
