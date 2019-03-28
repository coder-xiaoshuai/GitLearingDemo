package com.example.zhangshuai.activity;

import android.bluetooth.le.AdvertiseData;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.zhangshuai.adapter.CommonViewPageAdapter;
import com.example.zhangshuai.fragment.CommonFragment;
import com.example.zhangshuai.fragment.ViewPagerFragment;
import com.example.zhangshuai.gitlearingdemo.R;
import com.flyco.tablayout.SlidingTabLayout;

import java.util.ArrayList;
import java.util.List;

public class ViewPagerDemoActivity extends AppCompatActivity {
    //private List<Fragment> fragmentList;
    //private List<String> titles;
    //private ViewPager viewPager;
    //private CommonViewPageAdapter adapter;
    //private SlidingTabLayout tabLayout;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewpager_demo_2);

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.add(R.id.fragment_container,new ViewPagerFragment());
        transaction.commit();

        //viewPager = findViewById(R.id.viewpager);
        //tabLayout = findViewById(R.id.tab_layout);
        //initFragments();
        //initTitles();
        //adapter = new CommonViewPageAdapter(getSupportFragmentManager(),fragmentList,titles);
        //viewPager.setAdapter(adapter);
        //tabLayout.setViewPager(viewPager);
        //
        //findViewById(R.id.btn_add_fragment).setOnClickListener(v -> {
        //    fragmentList.add(CommonFragment.newInstance("新增的fragment"));
        //    titles.add("newTab");
        //    adapter.notifyDataSetChanged();
        //    tabLayout.notifyDataSetChanged();
        //});
        //
        //findViewById(R.id.btn_remove_fragment).setOnClickListener(v -> {
        //    fragmentList.remove(2);
        //    titles.remove(2);
        //    adapter.notifyDataSetChanged();
        //    tabLayout.notifyDataSetChanged();
        //});

    }


    //private void initFragments(){
    //    fragmentList = new ArrayList<>();
    //    fragmentList.add(CommonFragment.newInstance("第一个fragment"));
    //    fragmentList.add(CommonFragment.newInstance("第二个fragment"));
    //    fragmentList.add(CommonFragment.newInstance("第三个fragment"));
    //    fragmentList.add(CommonFragment.newInstance("第四个fragment"));
    //    fragmentList.add(CommonFragment.newInstance("第五个fragment"));
    //}
    //
    //private void initTitles() {
    //    titles = new ArrayList<>();
    //    titles.add("第一个fragment");
    //    titles.add("第二个fragment");
    //    titles.add("第三个fragment");
    //    titles.add("第四个fragment");
    //    titles.add("第五个fragment");
    //}

}
