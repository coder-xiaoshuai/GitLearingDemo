package com.example.zhangshuai.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.zhangshuai.gitlearingdemo.R;

import java.util.Random;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class TabFragment extends Fragment {
    public static final String ARGUMENTS_KEY_TAB_TITLE = "arguments_key_tab_title";
    private String tabTitle;
    private int[] colors = new int[] { Color.BLUE, Color.RED, Color.YELLOW, Color.GRAY, Color.GREEN };
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        tabTitle = getArguments().getString(ARGUMENTS_KEY_TAB_TITLE);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_tab, container, false);
        TextView textTabTile = rootView.findViewById(R.id.text_tab);
        textTabTile.setText(tabTitle);
        RelativeLayout bottom = rootView.findViewById(R.id.rv_bottom);
        Random random = new Random();
        //Log.e("zs","index"+random.nextInt(4));
        bottom.setBackgroundColor(colors[random.nextInt(4)]);
        return rootView;
    }
}
