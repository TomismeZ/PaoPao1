package com.example.zdk.paopao1.adapter;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.zdk.paopao1.R;
import com.example.zdk.paopao1.activity.ImageShowActivity;
import com.example.zdk.paopao1.bean.ImageInfo;


import java.util.List;

/**
 * Created by Administrator on 2017/12/28 0028.
 */

public class ImagesAdapter extends RecyclerView.Adapter<ImagesAdapter.ViewHolder>{
    private Context context;
    private List<ImageInfo> imageInfos;

    public ImagesAdapter(List<ImageInfo> imageInfos) {
        this.imageInfos = imageInfos;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if(context == null){
            context=parent.getContext();
        }
        View view= LayoutInflater.from(context).inflate(R.layout.item_image,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        final ImageInfo imageInfo = imageInfos.get(position);
        //加载图片
        Glide.with(context).load(imageInfo.getAvatar()).into(holder.userAvatar);
        holder.username.setText(imageInfo.getUsername());
        holder.content.setText(imageInfo.getContent());
        holder.publishTime.setText(imageInfo.getPublishTime());
        //添加元素给gridview
        holder.gridView.setAdapter(new GridViewAdapter(context, imageInfo.getImageUrls()));

        holder.gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent intent=new Intent(context, ImageShowActivity.class);
                intent.putExtra("imageurl",imageInfo.getImageUrls().get(position));
                context.startActivity(intent);
//                Glide.with(context).load( imageInfo.getImageUrls().get(position)).into(holder.image_content);

               /* final Dialog dialog=new Dialog(context,R.style.Dialog_FS); //设置全屏样式
                dialog.setContentView(R.layout.item_girdview_image); //设置dialog的布局
                ImageView imageView=view.findViewById(R.id.image_url);
                Glide.with(context).load( imageInfo.getImageUrls().get(position)).into(imageView);
                dialog.show();
                // 两秒后关闭后dialog
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        dialog.dismiss();
                    }
                }, 1000 * 2);*/
            }
        });
        Glide.with(context).load(imageInfo.getImageUrl()).into(holder.image_content);
        if (imageInfo.getAgree() > 0) {
            holder.good.setText("赞(" + imageInfo.getAgree() + ")");
        }


        if (imageInfo.getComment() > 0) {
            holder.comment.setText("评论(" + imageInfo.getComment() + ")");
        }
    }

    @Override
    public int getItemCount() {
        return imageInfos.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        CardView cardView;
        ImageView userAvatar;
        TextView username;
        TextView publishTime;
        TextView content;
        ImageView image_content;
        GridView gridView;
        TextView comment;
        TextView good;

        public ViewHolder(View view) {
            super(view);
            cardView = (CardView) view;
            userAvatar = view.findViewById(R.id.user_avatar);
            username = view.findViewById(R.id.username);
            publishTime = view.findViewById(R.id.user_publishtime);
            content = view.findViewById(R.id.content);
            image_content = view.findViewById(R.id.image_content);
            gridView = view.findViewById(R.id.gview);
            comment = view.findViewById(R.id.comment);
            good = view.findViewById(R.id.good);
        }
    }
}
