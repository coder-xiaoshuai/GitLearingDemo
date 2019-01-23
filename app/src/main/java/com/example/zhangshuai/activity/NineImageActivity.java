package com.example.zhangshuai.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;

import com.alexvasilkov.gestures.GestureController;
import com.alexvasilkov.gestures.commons.CropAreaView;
import com.alexvasilkov.gestures.views.GestureImageView;
import com.bumptech.glide.Glide;
import com.example.zhangshuai.gitlearingdemo.R;
import com.example.zhangshuai.utils.ViewUtils;
import com.example.zhangshuai.views.MyGestureImageView;

public class NineImageActivity extends AppCompatActivity {
    private MyGestureImageView gestureImageView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nine);
        //GestureImageView imageView = findViewById(R.id.gestureImageView);
        //imageView.setScaleType(ImageView.ScaleType.CENTER_INSIDE);

        gestureImageView = findViewById(R.id.gestureImageView);
        Glide.with(this)
            .load("http://img2.imgtn.bdimg.com/it/u=2808034745,2400772783&fm=26&gp=0.jpg")
            .into((ImageView) findViewById(R.id.gestureImageView));

        gestureImageView.getController().getSettings().setFillViewport(false);
        gestureImageView.getLayoutParams().height = ViewUtils.getScreenWidthPx(this);

        gestureImageView.getController().setOnGesturesListener(new GestureController.OnGestureListener() {
            @Override
            public void onDown(@NonNull MotionEvent event) {
                Log.e("zs", "onDown");
                gestureImageView.setDrawGridLine(true);
                gestureImageView.invalidate();
            }

            @Override
            public void onUpOrCancel(@NonNull MotionEvent event) {
                Log.e("zs", "onUpOrCancel");
                gestureImageView.setDrawGridLine(false);
                gestureImageView.invalidate();
            }

            @Override
            public boolean onSingleTapUp(@NonNull MotionEvent event) {
                Log.e("zs", "onSingleTapUp");
                return false;
            }

            @Override
            public boolean onSingleTapConfirmed(@NonNull MotionEvent event) {
                Log.e("zs", "onSingleTapConfirmed");
                return false;
            }

            @Override
            public void onLongPress(@NonNull MotionEvent event) {
                Log.e("zs", "onLongPress");
            }

            @Override
            public boolean onDoubleTap(@NonNull MotionEvent event) {
                Log.e("zs", "onDoubleTap");
                return false;
            }
        });

        gestureImageView.getController().setOnStateSourceChangeListener(new GestureController.OnStateSourceChangeListener() {
            @Override
            public void onStateSourceChanged(GestureController.StateSource source) {
                Log.e("zs", "onStateSourceChanged");
            }
        });

        //gestureImageView.getController()

    }
}
