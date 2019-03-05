package com.example.zhangshuai.activity;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.Target;
import com.example.zhangshuai.gitlearingdemo.R;

import java.util.ArrayList;
import java.util.List;

public class PreDownLoadActivity extends AppCompatActivity {
    private List<String> imageUrls;
    private List<ImageView> imageViews;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pre_download);
        initImageUrls();
        initImageViews();
        findViewById(R.id.btn_pre_download).setOnClickListener(v -> {
            for (int i = 0; i < imageUrls.size(); i++) {
                int finalI = i;
                Glide.with(PreDownLoadActivity.this).load(imageUrls.get(i)).listener(new RequestListener<Drawable>() {
                    @Override
                    public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target,
                                                boolean isFirstResource) {
                        Toast.makeText(PreDownLoadActivity.this, "加载失败"+e.toString(), Toast.LENGTH_SHORT).show();
                        Log.e("zs",e.toString());
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target,
                                                   DataSource dataSource, boolean isFirstResource) {
                        Toast.makeText(PreDownLoadActivity.this, "加载成功" + finalI, Toast.LENGTH_SHORT).show();
                        return false;
                    }
                }).preload();
            }
        });

        findViewById(R.id.btn_show).setOnClickListener(v -> {
            for (int i = 0; i < imageUrls.size(); i++) {
                RequestOptions options = new RequestOptions();
                options.dontAnimate();
                Glide.with(PreDownLoadActivity.this)
                    .load(imageUrls.get(i))
                    .apply(options)
                    .into(imageViews.get(i));
            }
        });
    }

    private void initImageUrls() {
        imageUrls = new ArrayList<>();
        imageUrls.add(
            "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1551787800328&di=6ebda714776515db33449be4ab7cc5ee&imgtype=0&src=http%3A%2F%2Fcms-bucket.nosdn.127.net%2Fcc19e6e73e6e4c9a8c3430a87ffe781020161001110859.gif");
        imageUrls.add(
            "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1551787821798&di=fb81d4eb86484b92ae5a46e2e5f2513f&imgtype=0&src=http%3A%2F%2Fphotocdn.sohu.com%2F20160206%2Fmp58312418_1454765249938_3.gif");
    }

    private void initImageViews() {
        imageViews = new ArrayList<>();
        imageViews.add(findViewById(R.id.image_left));
        imageViews.add(findViewById(R.id.image_right));
    }
}
