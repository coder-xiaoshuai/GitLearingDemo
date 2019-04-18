package com.example.zhangshuai.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.OvershootInterpolator;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.example.zhangshuai.gitlearingdemo.R;
import com.example.zhangshuai.utils.ViewUtils;

public class PraiseDemoActivity extends AppCompatActivity {
    private FrameLayout container;
    private ImageView left;
    private ImageView right;
    private ImageView center;
    private ImageView center_unlike;
    StateLike stateLike = StateLike.UNLIKE;

    enum StateLike {
        LIKE, UNLIKE
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_praise);
        container = findViewById(R.id.container);
        left = findViewById(R.id.left);
        right = findViewById(R.id.right);
        center = findViewById(R.id.center_like);
        center_unlike = findViewById(R.id.center_unlike);
        setState(StateLike.UNLIKE);
        container.setOnClickListener(v -> {
            if (stateLike == StateLike.UNLIKE) {
                startLikeAnimation();
            } else {
                startUnLikeAnimation();
            }
        });
    }

    private void setState(StateLike state) {
        stateLike = state;
        center_unlike.setVisibility(state == StateLike.UNLIKE ? View.VISIBLE : View.GONE);
        center.setVisibility(state == StateLike.LIKE ? View.VISIBLE : View.GONE);
    }

    private void startLikeAnimation() {
        int duration = 500;
        ScaleAnimation zoom =
            new ScaleAnimation(0.0f, 1.0f, 0.0f, 1.0f, Animation.RESTART, 0.5f, Animation.RESTART, 0.5f);
        zoom.setDuration(duration);
        zoom.setInterpolator(new OvershootInterpolator());
        center.startAnimation(zoom);

        AnimationSet leftSet = new AnimationSet(false);
        TranslateAnimation translateLeft =
            new TranslateAnimation(ViewUtils.dpToPx(this, 12), 0, ViewUtils.dpToPx(this, 15), 0);
        translateLeft.setDuration(duration);
        translateLeft.setInterpolator(new OvershootInterpolator());
        AlphaAnimation alphaLeft = new AlphaAnimation(0, 1);
        alphaLeft.setDuration((duration - 100) / 2);
        alphaLeft.setInterpolator(new DecelerateInterpolator());
        AlphaAnimation alphaLeft2 = new AlphaAnimation(1, 0);
        alphaLeft2.setDuration((duration - 100) / 2);
        alphaLeft2.setStartOffset((duration - 100) / 2);
        alphaLeft2.setInterpolator(new DecelerateInterpolator());
        leftSet.addAnimation(translateLeft);
        leftSet.addAnimation(alphaLeft);
        leftSet.addAnimation(alphaLeft2);
        leftSet.setFillAfter(true);
        left.startAnimation(leftSet);

        AnimationSet rightSet = new AnimationSet(false);
        TranslateAnimation translateRight =
            new TranslateAnimation(-ViewUtils.dpToPx(this, 12), 0, ViewUtils.dpToPx(this, 18), 0);
        translateRight.setDuration(duration);
        translateRight.setInterpolator(new OvershootInterpolator());
        AlphaAnimation alphaRight = new AlphaAnimation(0, 1);
        alphaRight.setDuration(duration);
        alphaRight.setInterpolator(new DecelerateInterpolator());
        AlphaAnimation alphaRight2 = new AlphaAnimation(1, 0);
        alphaRight2.setDuration((duration - 100) / 2);
        alphaRight2.setStartOffset((duration - 100) / 2);
        alphaRight2.setInterpolator(new DecelerateInterpolator());
        rightSet.addAnimation(translateRight);
        rightSet.addAnimation(alphaRight);
        rightSet.addAnimation(alphaRight2);
        rightSet.setFillAfter(true);
        right.startAnimation(rightSet);

        AlphaAnimation center_unlike_alpha = new AlphaAnimation(1, 0);
        center_unlike_alpha.setDuration(duration);
        center_unlike_alpha.setFillAfter(true);
        center_unlike.startAnimation(center_unlike_alpha);
        center_unlike_alpha.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                setState(StateLike.LIKE);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        center.setVisibility(View.VISIBLE);
    }

    private void startUnLikeAnimation() {
        int duration = 1000;
        ScaleAnimation zoom =
            new ScaleAnimation(1.0f, 0.0f, 1.0f, 0.0f, Animation.RESTART, 0.5f, Animation.RESTART, 0.5f);
        zoom.setDuration(duration);
        center.startAnimation(zoom);

        AnimationSet leftSet = new AnimationSet(false);
        //TranslateAnimation translateLeft =
        //    new TranslateAnimation(0, ViewUtils.dpToPx(this, 12), 0, ViewUtils.dpToPx(this, 9));
        TranslateAnimation translateLeft =
            new TranslateAnimation(0, ViewUtils.dpToPx(this, 12), 0, ViewUtils.dpToPx(this, 15));
        translateLeft.setDuration(duration);
        translateLeft.setInterpolator(new OvershootInterpolator());
        AlphaAnimation alphaLeft = new AlphaAnimation(1, 0);
        alphaLeft.setDuration(duration);
        alphaLeft.setInterpolator(new DecelerateInterpolator());
        //AlphaAnimation alphaLeft2 = new AlphaAnimation(1, 0);
        //alphaLeft2.setDuration((duration - 100) / 2);
        //alphaLeft2.setStartOffset((duration - 100) / 2);
        //alphaLeft2.setInterpolator(new DecelerateInterpolator());
        leftSet.addAnimation(translateLeft);
        leftSet.addAnimation(alphaLeft);
        //leftSet.addAnimation(alphaLeft2);
        leftSet.setFillAfter(true);
        leftSet.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                setState(StateLike.UNLIKE);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        left.startAnimation(leftSet);

        AnimationSet rightSet = new AnimationSet(false);
        //TranslateAnimation translateRight =
        //    new TranslateAnimation(0, ViewUtils.dpToPx(this, 0), 0, -ViewUtils.dpToPx(this, 6));
        TranslateAnimation translateRight =
            new TranslateAnimation(0, -ViewUtils.dpToPx(this, 12), 0, ViewUtils.dpToPx(this, 18));
        translateRight.setDuration(duration);
        translateRight.setInterpolator(new OvershootInterpolator());
        AlphaAnimation alphaRight = new AlphaAnimation(1, 0);
        alphaRight.setDuration(duration);
        alphaRight.setInterpolator(new DecelerateInterpolator());
        //AlphaAnimation alphaRight2 = new AlphaAnimation(1, 0);
        //alphaRight2.setDuration((duration - 100) / 2);
        //alphaRight2.setStartOffset((duration - 100) / 2);
        //alphaRight2.setInterpolator(new DecelerateInterpolator());
        rightSet.addAnimation(translateRight);
        rightSet.addAnimation(alphaRight);
        //rightSet.addAnimation(alphaRight2);
        rightSet.setFillAfter(true);
        right.startAnimation(rightSet);

        AlphaAnimation show = new AlphaAnimation(0, 1);
        show.setDuration(duration);
        center_unlike.startAnimation(show);
        center_unlike.setVisibility(View.VISIBLE);
    }
}
