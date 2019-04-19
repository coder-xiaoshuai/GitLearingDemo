package com.example.zhangshuai.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.zhangshuai.adapter.CommonViewPageAdapter;
import com.example.zhangshuai.gitlearingdemo.R;
import com.flyco.tablayout.SlidingTabLayout;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

public class ViewPagerFragment extends Fragment {
    private List<Fragment> fragmentList;
    private List<String> titles;
    private ViewPager viewPager;
    private CommonViewPageAdapter adapter;
    private SlidingTabLayout tabLayout;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.activity_viewpager_demo,container,false);

        viewPager = rootView.findViewById(R.id.viewpager);
        tabLayout = rootView.findViewById(R.id.tab_layout);
        initFragments();
        initTitles();
        adapter = new CommonViewPageAdapter(getChildFragmentManager(),fragmentList,titles);
        viewPager.setAdapter(adapter);
        tabLayout.setViewPager(viewPager);

        rootView.findViewById(R.id.btn_add_fragment).setOnClickListener(v -> {
            fragmentList.add(CommonFragment.newInstance("新增的fragment"));
            titles.add("newTab");
            adapter.notifyDataSetChanged();
            tabLayout.notifyDataSetChanged();
        });

        rootView.findViewById(R.id.btn_remove_fragment).setOnClickListener(v -> {
            fragmentList.remove(2);
            titles.remove(2);
            adapter.notifyDataSetChanged();
            tabLayout.notifyDataSetChanged();
        });

        return rootView;
    }

    private void initFragments(){
        fragmentList = new ArrayList<>();
        fragmentList.add(CommonFragment.newInstance("第一个fragment"));
        fragmentList.add(CommonFragment.newInstance("第二个fragment"));
        fragmentList.add(CommonFragment.newInstance("第三个fragment"));
        fragmentList.add(CommonFragment.newInstance("第四个fragment"));
        fragmentList.add(CommonFragment.newInstance("第五个fragment"));
    }

    private void initTitles() {
        titles = new ArrayList<>();
        titles.add("第一个fragment");
        titles.add("第二个fragment");
        titles.add("第三个fragment");
        titles.add("第四个fragment");
        titles.add("第五个fragment");
    }
}
