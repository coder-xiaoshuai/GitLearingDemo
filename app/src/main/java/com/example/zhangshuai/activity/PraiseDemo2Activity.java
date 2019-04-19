package com.example.zhangshuai.activity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnticipateInterpolator;
import android.view.animation.OvershootInterpolator;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;

import com.example.zhangshuai.gitlearingdemo.R;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

public class PraiseDemo2Activity extends AppCompatActivity {
    private ConstraintLayout constraintLayout;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_praise_2);
        constraintLayout = findViewById(R.id.animation_container);
        constraintLayout.setOnClickListener(v -> startAnimation());
    }

    private void startAnimation() {
        for (int i = 0; i < constraintLayout.getChildCount(); i++) {
            View childView = constraintLayout.getChildAt(i);
            if (childView instanceof ImageView) {
                if (childView.getId() == R.id.center) {
                    AnimationSet animationSet = new AnimationSet(false);
                    ScaleAnimation zoom =
                        new ScaleAnimation(0.0f, 1.1f, 0.0f, 1.1f, Animation.RESTART, 0.5f, Animation.RESTART, 0.5f);
                    zoom.setDuration(700);
                    zoom.setInterpolator(new OvershootInterpolator());
                    zoom.setFillAfter(true);
                    ScaleAnimation shrink =
                        new ScaleAnimation(1.1f, 0f, 1.1f, 0f, Animation.RESTART, 0.5f, Animation.RESTART, 0.5f);
                    shrink.setStartOffset(800);
                    shrink.setInterpolator(new AnticipateInterpolator());
                    shrink.setDuration(200);
                    AlphaAnimation alphaAnimation = new AlphaAnimation(1, 0);
                    alphaAnimation.setStartOffset(800);
                    alphaAnimation.setDuration(200);
                    animationSet.addAnimation(zoom);
                    animationSet.addAnimation(shrink);
                    animationSet.addAnimation(alphaAnimation);
                    animationSet.setFillAfter(true);
                    childView.setAlpha(1);
                    childView.startAnimation(animationSet);
                } else {
                    ConstraintLayout.LayoutParams params = (ConstraintLayout.LayoutParams) childView.getLayoutParams();
                    float angle = params.circleAngle;
                    int radius = params.circleRadius;
                    childView.setAlpha(1);
                    startAnimationForChildView(childView, angle, 140);
                }
            }
        }
    }

    private void startAnimationForChildView(View view, float circleAngle, int radius) {
        int duration = 700;
        int startOffset = 300;
        AnimationSet animationSet = new AnimationSet(true);
        double fromXDelta = (float) Math.sin(Math.toRadians(circleAngle)) * radius;
        double fromYDelta = (float) Math.cos(Math.toRadians(circleAngle)) * radius;
        Log.e("zs",
            "fromXDelta" + fromXDelta + ",fromYDelta" + fromYDelta + ",circleAngle" + circleAngle + ",radius" + radius);
        //TranslateAnimation translateAnimation =
        //    new TranslateAnimation(-(float) Math.sin(Math.toRadians(circleAngle)) * radius, 0,
        //        (float) Math.cos(Math.toRadians(circleAngle)) * radius, 0);
        TranslateAnimation translateAnimation =
            new TranslateAnimation(0, (float) Math.sin(Math.toRadians(circleAngle)) * radius, 0,
                -(float) Math.cos(Math.toRadians(circleAngle)) * radius);
        translateAnimation.setStartOffset(startOffset);
        translateAnimation.setDuration(duration);
        AlphaAnimation alphaAnimation = new AlphaAnimation(1, 0);
        alphaAnimation.setStartOffset(800);
        alphaAnimation.setDuration(200);
        ScaleAnimation zoom =
            new ScaleAnimation(0.0f, 1.0f, 0.0f, 1.0f, Animation.RESTART, 0.5f, Animation.RESTART, 0.5f);
        zoom.setStartOffset(startOffset);
        zoom.setDuration(duration);
        animationSet.addAnimation(translateAnimation);
        animationSet.addAnimation(alphaAnimation);
        animationSet.addAnimation(zoom);
        animationSet.setFillAfter(true);

        view.startAnimation(animationSet);
    }
}
