package com.example.commonui.widget;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.TimeInterpolator;
import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.example.commonui.R;

import java.util.Random;

public class Love extends RelativeLayout {
    private Context mContext;
    float[] num = { -30, -20, 0, 20, 30 };//随机心形图片的家督

    public Love(Context context) {
        super(context);
    }

    public Love(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public Love(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        final ImageView imageView = new ImageView(getContext());
        LayoutParams params = new LayoutParams(300, 300);
        params.leftMargin = (int) (event.getX() - 150);
        params.topMargin = (int) (event.getY() - 300);
        imageView.setImageDrawable(getResources().getDrawable(R.drawable.ic_launcher));
        imageView.setLayoutParams(params);
        addView(imageView);

        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.play(scale(imageView, "scaleX", 2f, 0.9f, 100, 0))
            .with(scale(imageView, "scaleY", 2f, 0.9f, 100, 0))
            .with(rotation(imageView, 0, 0, num[new Random().nextInt(4)]))
            .with(alpha(imageView, 0f, 1f, 100, 0))
            .with(scale(imageView, "scaleX", 0.9f, 1f, 50, 150))
            .with(scale(imageView, "scaleY", 0.9f, 1f, 50, 150))
            .with(translation(imageView, "translationY", 0, -600, 800, 400))
            .with(alpha(imageView, 1, 0, 300, 400))
            .with(scale(imageView, "scaleX", 1f, 3f, 700, 400))
            .with(scale(imageView, "scaleY", 1, 3f, 700, 400));
        animatorSet.start();
        animatorSet.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                removeViewInLayout(imageView);
            }
        });

        return super.onTouchEvent(event);
    }

    public ObjectAnimator scale(View view, String propertyName, float from, float to, long time, long delayTime) {
        ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(view, propertyName, from, to);
        objectAnimator.setInterpolator(new LinearInterpolator());
        objectAnimator.setStartDelay(delayTime);
        objectAnimator.setDuration(time);
        return objectAnimator;
    }

    public ObjectAnimator translation(View view, String propertyName, float from, float to, long time, long delayTime) {
        ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(view, propertyName, from, to);
        objectAnimator.setInterpolator(new LinearInterpolator());
        objectAnimator.setStartDelay(delayTime);
        objectAnimator.setDuration(time);
        return objectAnimator;
    }

    public ObjectAnimator alpha(View view, float from, float to, long time, long delayTime) {
        ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(view, "alpha", from, to);
        objectAnimator.setInterpolator(new LinearInterpolator());
        objectAnimator.setStartDelay(delayTime);
        objectAnimator.setDuration(time);
        return objectAnimator;
    }

    public ObjectAnimator rotation(View view, long time, long delayTime, float... values) {
        ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(view, "rotation", values);
        objectAnimator.setDuration(time);
        objectAnimator.setStartDelay(delayTime);
        objectAnimator.setInterpolator(new TimeInterpolator() {
            @Override
            public float getInterpolation(float input) {
                return input;
            }
        });
        return objectAnimator;
    }
}
