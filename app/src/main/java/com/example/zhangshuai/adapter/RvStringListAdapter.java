package com.example.zhangshuai.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.zhangshuai.gitlearingdemo.R;

import java.util.List;

public class RvStringListAdapter extends RecyclerView.Adapter<RvStringListAdapter.MyViewHolder> {
    private List<String> mStrings;
    private Context mContext;

    public RvStringListAdapter(Context context, List<String> mStrings){
        this.mContext = context;
        this.mStrings = mStrings;
    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new MyViewHolder(LayoutInflater.from(mContext).inflate(R.layout.item_rv_string_list, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {
        myViewHolder.textView.setText(mStrings.get(i));
    }

    @Override
    public int getItemCount() {
        return mStrings.size();
    }

    static class MyViewHolder extends RecyclerView.ViewHolder{
        private TextView textView;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.text);
        }
    }
}

