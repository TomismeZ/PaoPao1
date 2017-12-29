package com.example.zdk.paopao1.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.zdk.paopao1.fragment.CasualFragment;
import com.example.zdk.paopao1.fragment.ImageFragment;
import com.example.zdk.paopao1.fragment.VoiceFragment;
import com.example.zdk.paopao1.fragment.WordsFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/12/29 0029.
 */

public class HomePagerAdapter extends FragmentPagerAdapter {
    List<Fragment> fragments=new ArrayList<Fragment>();

    //构造方法    初始化一些数据    传参
    public HomePagerAdapter(FragmentManager fm) {
        super(fm);
        //初始化4个 fragment
        VoiceFragment voiceFragment=new VoiceFragment();
        WordsFragment wordsFragment=new WordsFragment();
        ImageFragment imageFragment=new ImageFragment();
        CasualFragment casualFragment=new CasualFragment();
        fragments.add(voiceFragment);
        fragments.add(wordsFragment);
        fragments.add(imageFragment);
        fragments.add(casualFragment);
    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }
}
