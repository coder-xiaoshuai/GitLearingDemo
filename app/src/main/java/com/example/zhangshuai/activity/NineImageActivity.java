package com.example.zhangshuai.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.alexvasilkov.gestures.commons.CropAreaView;
import com.alexvasilkov.gestures.views.GestureImageView;
import com.bumptech.glide.Glide;
import com.example.zhangshuai.gitlearingdemo.R;

public class NineImageActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nine);
        GestureImageView imageView = findViewById(R.id.gestureImageView);
        //imageView.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
        Glide.with(this).load("http://img2.imgtn.bdimg.com/it/u=2808034745,2400772783&fm=26&gp=0.jpg").into(imageView);

        CropAreaView cropAreaView = findViewById(R.id.cropAreaView);
        cropAreaView.setImageView(imageView);

        //ImageView imageView2 = findViewById(R.id.image);
        //imageView2.setScaleType(ImageView.ScaleType.MATRIX);
        //Glide.with(this).load("http://img2.imgtn.bdimg.com/it/u=2808034745,2400772783&fm=26&gp=0.jpg").into(imageView2);
    }
}
