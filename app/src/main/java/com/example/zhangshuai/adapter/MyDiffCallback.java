package com.example.zhangshuai.adapter;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.util.DiffUtil;

import java.util.List;

public class MyDiffCallback extends DiffUtil.Callback {
    private List<String> oldData;
    private List<String> newData;

    public MyDiffCallback(List<String> oldData, List<String> newData) {
        this.newData = newData;
        this.oldData = oldData;
    }

    @Override
    public int getOldListSize() {
        return oldData.size();
    }

    @Override
    public int getNewListSize() {
        return newData.size();
    }

    @Override
    public boolean areItemsTheSame(int i, int i1) {
        return oldData.get(i).equals(newData.get(i1));
    }

    @Override
    public boolean areContentsTheSame(int i, int i1) {
        return oldData.get(i).equals(newData.get(i1));
    }

    @Nullable
    @Override
    public Object getChangePayload(int oldItemPosition, int newItemPosition) {
        Bundle diffBundle = new Bundle();
        diffBundle.putString("key", newData.get(newItemPosition));
        return diffBundle;
    }
}
