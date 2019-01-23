package com.example.zhangshuai.views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class CustomSurfaceView extends SurfaceView implements SurfaceHolder.Callback {
    public CustomSurfaceView(Context context) {
        super(context);
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {

    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {

    }

    class LoopThread extends Thread {

        SurfaceHolder surfaceHolder;
        Context context;
        boolean isRuning;
        float radius = 10f;
        Paint paint;

        public LoopThread(SurfaceHolder surfaceHolder, Context context) {
            this.surfaceHolder = surfaceHolder;
            this.context = context;
            isRuning = false;

            paint = new Paint();
            paint.setColor(Color.GRAY);
            paint.setStyle(Paint.Style.STROKE);
        }

        @Override
        public void run() {
            super.run();

            Canvas canvas = null;
            while (isRuning) {
                synchronized (surfaceHolder) {

                    try {
                        canvas = surfaceHolder.lockCanvas();
                        //draw someThing
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}
