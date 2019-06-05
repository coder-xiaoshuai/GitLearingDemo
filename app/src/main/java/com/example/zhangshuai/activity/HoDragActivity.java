package com.example.zhangshuai.activity;

import android.app.Activity;
import android.content.ClipData;
import android.graphics.Canvas;
import android.graphics.Point;
import android.os.Bundle;
import android.view.DragEvent;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;

import com.example.zhangshuai.gitlearingdemo.R;

import java.lang.ref.WeakReference;

public class HoDragActivity extends Activity {

    private LinearLayout main;

    private GestureDetector mGestureDetector;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drag_test);

        main = (LinearLayout) findViewById(R.id.main);

        bindDragListener(R.id.btn1);
        bindDragListener(R.id.btn2);
        bindDragListener(R.id.btn3);
        bindDragListener(R.id.btn4);
        bindDragListener(R.id.btn5);
        bindDragListener(R.id.btn6);
        bindDragListener(R.id.btn7);
        bindDragListener(R.id.btn8);
        bindDragListener(R.id.btn9);
        bindDragListener(R.id.btn10);
        bindDragListener(R.id.btn11);

        mGestureDetector = new GestureDetector(this, new DragGestureListener());
    }

    private View mDragView;

    private void bindDragListener(int id) {
        View v = findViewById(id);
        v.setOnTouchListener(mOnTouchListener);
        v.setOnDragListener(mOnDragListener);
    }

    private View.OnTouchListener mOnTouchListener = new View.OnTouchListener() {

        @Override
        public boolean onTouch(View v, MotionEvent event) {
            mDragView = v;

            if (mGestureDetector.onTouchEvent(event)) return true;

            switch (event.getAction() & MotionEvent.ACTION_MASK) {
                case MotionEvent.ACTION_UP:

                    break;
            }

            return false;
        }
    };

    private View.OnDragListener mOnDragListener = new View.OnDragListener() {

        @Override
        public boolean onDrag(View v, DragEvent event) {
            switch (event.getAction()) {
                case DragEvent.ACTION_DRAG_STARTED:
                    // Do nothing
                    break;
                case DragEvent.ACTION_DRAG_ENTERED:
                    v.setAlpha(0.5F);
                    break;
                case DragEvent.ACTION_DRAG_EXITED:
                    v.setAlpha(1F);
                    break;
                case DragEvent.ACTION_DROP:
                    View view = (View) event.getLocalState();
                    for (int i = 0, j = main.getChildCount(); i < j; i++) {
                        if (main.getChildAt(i) == v) {
                            // 当前位置
                            main.removeView(view);
                            main.addView(view, i);
                            break;
                        }
                    }
                    break;
                case DragEvent.ACTION_DRAG_ENDED:
                    v.setAlpha(1F);
                default:
                    break;
            }
            return true;
        }
    };

    private class DragGestureListener extends GestureDetector.SimpleOnGestureListener {
        @Override
        public boolean onSingleTapConfirmed(MotionEvent e) {
            return super.onSingleTapConfirmed(e);
        }

        @Override
        public void onLongPress(MotionEvent e) {
            super.onLongPress(e);
            ClipData data = ClipData.newPlainText("", "");
            MyDragShadowBuilder shadowBuilder = new MyDragShadowBuilder(mDragView);
            mDragView.startDrag(data, shadowBuilder, mDragView, 0);
        }

        @Override
        public boolean onDown(MotionEvent e) {
            return true;
        }
    }

    private class MyDragShadowBuilder extends View.DragShadowBuilder {

        private final WeakReference<View> mView;

        public MyDragShadowBuilder(View view) {
            super(view);
            mView = new WeakReference<View>(view);
        }

        @Override
        public void onDrawShadow(Canvas canvas) {
            canvas.scale(1.5F, 1.5F);
            super.onDrawShadow(canvas);
        }

        @Override
        public void onProvideShadowMetrics(Point shadowSize, Point shadowTouchPoint) {
            // super.onProvideShadowMetrics(shadowSize, shadowTouchPoint);

            final View view = mView.get();
            if (view != null) {
                shadowSize.set((int) (view.getWidth() * 1.5F), (int) (view.getHeight() * 1.5F));
                shadowTouchPoint.set(shadowSize.x / 2, shadowSize.y / 2);
            } else {
                // Log.e(View.VIEW_LOG_TAG,
                // "Asked for drag thumb metrics but no view");
            }
        }
    }
}
