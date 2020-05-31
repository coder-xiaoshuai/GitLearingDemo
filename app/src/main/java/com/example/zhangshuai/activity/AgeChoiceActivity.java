package com.example.zhangshuai.activity;

import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.zhangshuai.adapter.AgeChoiceAdapter;
import com.example.zhangshuai.entity.AgeNormalModel;
import com.example.zhangshuai.entity.AgePlaceholderModel;
import com.example.zhangshuai.entity.AgeRangeModel;
import com.example.zhangshuai.entity.BaseAgeModel;
import com.example.zhangshuai.gitlearingdemo.R;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class AgeChoiceActivity extends AppCompatActivity {
    private List<BaseAgeModel> ageData;
    private LinkedHashMap<Integer, Integer> ageRangeMap = new LinkedHashMap<>();
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_age_choice);
        initAgeData();
        initData();

        recyclerView = findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 6));
        recyclerView.setAdapter(new AgeChoiceAdapter(this, ageData));

    }

    private void initData() {
        ageRangeMap.put(0, 2000);
        ageRangeMap.put(9, 1990);
        ageRangeMap.put(8, 1980);
        ageRangeMap.put(7, 1970);
        ageRangeMap.put(6, 1960);
        ageRangeMap.put(5, 1950);
        ageRangeMap.put(4, 1940);
//        int currentYear = Calendar.getInstance().get(Calendar.YEAR);
        int currentYear = 2021;
        LinkedHashMap<Integer, List<Integer>> data = new LinkedHashMap<>();
        for (Map.Entry<Integer, Integer> entry : ageRangeMap.entrySet()) {
            int min = getMin(currentYear, entry.getKey());
            int max = getMax(currentYear, entry.getKey());
            ArrayList<Integer> list1 = new ArrayList<>();
            for (int i = min; i <= max; i++) {
                if (i > 75) {
                    continue;
                }
                list1.add(i);
            }
//            Log.i("zs","list1 size"+list1.size());
//            if (list1.size() > 0 && list1.size() < 5) {
//                int length  = list1.size();
//                for (int i = 0; i < 5 - length; i++) {
//                    list1.add(0);
//                    Log.i("zs","添加了占位");
//                }
//            }
//            if (list1.size() > 5 && list1.size() < 10) {
////                list1.add(5, 0);
//                for (int i = 0; i < 10 - list1.size(); i++) {
//                    list1.add(0);
//                }
//            }
            data.put(entry.getKey(), list1);
            Log.i("zs", data.toString());
        }
        ageData = new ArrayList<>();
        for (Map.Entry<Integer, List<Integer>> entry : data.entrySet()) {
            List<Integer> ages = entry.getValue();
            if (ages.size() > 0) {
                ageData.add(new AgeRangeModel(entry.getKey() + "0后"));
            }

            if (ages.size() > 0 && ages.size() <= 5) {
                for (int i = 0; i < ages.size(); i++) {
                    ageData.add(new AgeNormalModel(ages.get(i), false));
                }
                int placeCount = 5 - ages.size();
                for (int i = 0; i < placeCount; i++) {
                    ageData.add(new AgePlaceholderModel());
                }
            }
            if (ages.size() > 5 && ages.size() <= 10) {
                for (int i = 0; i < 5; i++) {
                    ageData.add(new AgeNormalModel(ages.get(i), false));
                }
                ageData.add(new AgePlaceholderModel());
                for (int i = 5; i < ages.size(); i++) {
                    ageData.add(new AgeNormalModel(ages.get(i), false));
                }

                int placeCount = 10 - ages.size();
                for (int i = 0; i < placeCount; i++) {
                    ageData.add(new AgePlaceholderModel());
                }
            }
        }
    }

    //     /**
//     * 获取年代对应的最大年龄
//     */
//    fun getMax (curYear: Int, target: Int) : Int {
//        if (target == 0) {
//            return curYear - 2000
//        }
//
//        return curYear - map?.get(target)!!
//    }
//
//    /**
//     * 获取最大年龄后，减10岁+1岁
//     */
//    fun getMin (curYear: Int, target: Int) : Int {
//        if (target == 0) {
//            return 18
//        }
//
//        return getMax (curYear, target) - 10 + 1
//    }
    private int getMax(int currentYear, int target) {
        if (target == 0) {
            return currentYear - 2000;
        }
        return currentYear - ageRangeMap.get(target);
    }

    private int getMin(int currentYear, int target) {
        if (target == 0) {
            return 18;
        }
        return getMax(currentYear, target) - 10 + 1;
    }

    private void initAgeData() {

    }
}
