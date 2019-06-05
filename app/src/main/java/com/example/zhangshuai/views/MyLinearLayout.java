package com.example.zhangshuai.views;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.LinearLayout;

public class MyLinearLayout extends LinearLayout {
    public MyLinearLayout(Context context) {
        super(context);
    }

    public MyLinearLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyLinearLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                Log.e("zs", getClass().getSimpleName() + "的dispatchTouchEvent的MotionEvent.ACTION_DOWN");
                break;
            case MotionEvent.ACTION_UP:
                Log.e("zs", getClass().getSimpleName() + "的dispatchTouchEvent的MotionEvent.ACTION_UP");
                break;
            case MotionEvent.ACTION_MOVE:
                Log.e("zs", getClass().getSimpleName() + "的dispatchTouchEvent的MotionEvent.ACTION_MOVE");
                break;
            case MotionEvent.ACTION_CANCEL:
                Log.e("zs", getClass().getSimpleName() + "的dispatchTouchEvent的MotionEvent.ACTION_CANCEL");
                break;
        }
        return super.dispatchTouchEvent(ev);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                Log.e("zs", getClass().getSimpleName() + "的onInterceptTouchEvent的MotionEvent.ACTION_DOWN");
                break;
            case MotionEvent.ACTION_MOVE:
                Log.e("zs", getClass().getSimpleName() + "的onInterceptTouchEvent的MotionEvent.ACTION_MOVE");
                break;
            case MotionEvent.ACTION_UP:
                Log.e("zs", getClass().getSimpleName() + "的onInterceptTouchEvent的MotionEvent.ACTION_UP");
                break;
            case MotionEvent.ACTION_CANCEL:
                Log.e("zs", getClass().getSimpleName() + "的onInterceptTouchEvent的MotionEvent.ACTION_CANCEL");
                break;
        }
        return super.onInterceptTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                Log.e("zs", getClass().getSimpleName() + "的onTouchEvent的MotionEvent.ACTION_DOWN");
                break;
            case MotionEvent.ACTION_UP:
                Log.e("zs", getClass().getSimpleName() + "的onTouchEvent的MotionEvent.ACTION_UP");
                break;
            case MotionEvent.ACTION_MOVE:
                Log.e("zs", getClass().getSimpleName() + "的onTouchEvent的MotionEvent.ACTION_MOVE");
                break;
            case MotionEvent.ACTION_CANCEL:
                Log.e("zs", getClass().getSimpleName() + "的onTouchEvent的MotionEvent.ACTION_CANCEL");
                break;
        }
        return super.onTouchEvent(event);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        Log.e("zs", "onMeasure   MyLinearLayout");
    }

    @Override
    protected void dispatchDraw(Canvas canvas) {
        super.dispatchDraw(canvas);
        Log.e("zs", "dispatchDraw   MyLinearLayout");
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Log.e("zs", "onDraw   MyLinearLayout");
    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
        Log.e("zs", "draw   MyLinearLayout");
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
        Log.e("zs", "onLayout   MyLinearLayout");
    }
}
