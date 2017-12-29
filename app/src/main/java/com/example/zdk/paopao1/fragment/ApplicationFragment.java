package com.example.zdk.paopao1.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.zdk.paopao1.R;
import com.example.zdk.paopao1.activity.OpenDoorActivity;


/**
 * Created by Administrator on 2017/12/28 0028.
 */

public class ApplicationFragment extends Fragment {
    private LinearLayout openDoor;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_application,null);
        initView(view); //初始化组件
        return view;
    }

    /**
     * 初始化组件
     * @param view
     */
    private void initView(View view) {
        openDoor=view.findViewById(R.id.open_door);
        openDoor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Toast.makeText(getActivity(),"公司开门",Toast.LENGTH_SHORT).show();
                Intent intent=new Intent(getActivity(), OpenDoorActivity.class);
                startActivity(intent);
            }
        });
    }
}
