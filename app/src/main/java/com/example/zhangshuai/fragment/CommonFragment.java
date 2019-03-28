package com.example.zhangshuai.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.zhangshuai.adapter.RvStringListAdapter;
import com.example.zhangshuai.gitlearingdemo.R;

import java.util.ArrayList;
import java.util.List;

public class CommonFragment extends Fragment {
    public static final String KEY_FRAGMENT_NAME = "key_fragment_name";
    private RecyclerView recyclerView;
    private List<String> mDatas = new ArrayList<>();
    private RvStringListAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_common, container, false);
        String fragmentName = getArguments().getString(KEY_FRAGMENT_NAME);
        TextView contentView = rootView.findViewById(R.id.text_fragment);
        recyclerView = rootView.findViewById(R.id.recyclerview);
        contentView.setText(fragmentName);
        LinearLayoutManager manager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(manager);
        adapter = new RvStringListAdapter(getContext(), mDatas);
        recyclerView.setAdapter(adapter);
        contentView.setOnClickListener(v -> {
            addDatas();
            adapter.notifyDataSetChanged();
        });

        return rootView;
    }

    public static CommonFragment newInstance(String fragmentName) {
        CommonFragment commonFragment = new CommonFragment();
        Bundle bundle = new Bundle();
        bundle.putString(KEY_FRAGMENT_NAME, fragmentName);
        commonFragment.setArguments(bundle);
        return commonFragment;
    }

    private void addDatas() {
        for (int i = 0; i < 20; i++) {
            mDatas.add("ITEM" + (i + 1));
        }
    }
}
