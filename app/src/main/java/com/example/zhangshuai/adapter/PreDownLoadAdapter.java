package com.example.zhangshuai.adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.Target;
import com.example.zhangshuai.gitlearingdemo.R;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

public class PreDownLoadAdapter extends RecyclerView.Adapter<PreDownLoadAdapter.SingleImageViewHolder> {
    private List<String> mData;
    private Context mContext;

    public PreDownLoadAdapter(Context context, List<String> data) {
        this.mContext = context;
        this.mData = data;
    }

    @NonNull
    @Override
    public SingleImageViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new SingleImageViewHolder(
            LayoutInflater.from(mContext).inflate(R.layout.item_rv_pre_download, null, false));
    }

    @Override
    public void onBindViewHolder(@NonNull SingleImageViewHolder singleImageViewHolder, int i) {
        Log.e("zs", "position:" + i);
        RequestOptions options = new RequestOptions();

        options.diskCacheStrategy(DiskCacheStrategy.AUTOMATIC);
        //options.override(1080,500);
        options.dontAnimate();
        Glide.with(mContext).load(mData.get(i)).listener(new RequestListener<Drawable>() {
            @Override
            public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target,
                                        boolean isFirstResource) {
                Toast.makeText(mContext, "加载失败" + e.toString(), Toast.LENGTH_SHORT).show();
                Log.e("zs",e.toString());
                return false;
            }

            @Override
            public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target,
                                           DataSource dataSource, boolean isFirstResource) {
                Toast.makeText(mContext, "加载成功" + i, Toast.LENGTH_SHORT).show();
                return false;
            }
        }).apply(options).into(singleImageViewHolder.singleImage);
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    static class SingleImageViewHolder extends RecyclerView.ViewHolder {
        private ImageView singleImage;

        public SingleImageViewHolder(@NonNull View itemView) {
            super(itemView);
            singleImage = itemView.findViewById(R.id.item_image);
        }
    }
}
