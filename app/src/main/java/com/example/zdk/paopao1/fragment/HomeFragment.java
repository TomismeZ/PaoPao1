package com.example.zdk.paopao1.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.example.zdk.paopao1.R;
import com.example.zdk.paopao1.adapter.HomePagerAdapter;


/**
 * Created by Administrator on 2017/12/28 0028.
 */

public class HomeFragment extends Fragment {
    ViewPager viewPager;
    RadioGroup radioGroup;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_home,null);
        initView(view); //初始化组件
        return view;
    }

    /**
     * 初始化组件
     * @param view
     */
    private void initView(View view) {
        viewPager=view.findViewById(R.id.vp_home);
        radioGroup=view.findViewById(R.id.rg_home);
        // 给viewpager中填充内容--》fragment
        // 我们 需要4个fragment 用来给viewpager做内容
        // adapter适配器 adapterview
        // getFragmentManager getChildFragmentManager
        viewPager.setAdapter(new HomePagerAdapter(this.getChildFragmentManager()));
        radioGroup.setOnCheckedChangeListener(new HomeRadioListener());
        viewPager.addOnPageChangeListener(new HomePagerListener());
    }

    /**
     * 单选按钮点击事件
     */
    class HomeRadioListener implements RadioGroup.OnCheckedChangeListener{

        @Override
        public void onCheckedChanged(RadioGroup group, int checkedId) {
            switch (checkedId){
                case R.id.rb_voice:
                    viewPager.setCurrentItem(0,false);
                    break;
                case R.id.rb_words:
                    viewPager.setCurrentItem(1,false);
                    break;
                case R.id.rb_image:
                    viewPager.setCurrentItem(2,false);
                    break;
                case R.id.rb_casual:
                    viewPager.setCurrentItem(3,false);
                    break;
                    default:
                        break;
            }
        }
    }

    /**
     * 滑动监听事件
     */
    class HomePagerListener implements ViewPager.OnPageChangeListener{

        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            ((RadioButton) radioGroup.getChildAt(position)).setChecked(true);
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    }
}
