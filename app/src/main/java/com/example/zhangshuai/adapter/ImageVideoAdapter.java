package com.example.zhangshuai.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.zhangshuai.base.GlideApp;
import com.example.zhangshuai.entity.ImageVideoEntity;
import com.example.zhangshuai.gitlearingdemo.R;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ImageVideoAdapter extends RecyclerView.Adapter<ImageVideoAdapter.MyViewHolder> {
    private Context mContext;
    private List<ImageVideoEntity> mDatas;

    public ImageVideoAdapter(Context mContext, List<ImageVideoEntity> mDatas) {
        this.mContext = mContext;
        this.mDatas = mDatas;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new MyViewHolder(LayoutInflater.from(mContext).inflate(R.layout.item_rv_image, null, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {
        Log.e("zs",mDatas.get(i).getPath());
        GlideApp.with(mContext).load(mDatas.get(i).getPath()).into(myViewHolder.imageView);
    }

    @Override
    public int getItemCount() {
        return mDatas == null ? 0 : mDatas.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        private ImageView imageView;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.image_pic);
        }
    }
}
