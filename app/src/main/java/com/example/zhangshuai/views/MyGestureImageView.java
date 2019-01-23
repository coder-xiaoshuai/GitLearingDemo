package com.example.zhangshuai.views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;

import com.alexvasilkov.gestures.views.GestureImageView;
import com.example.zhangshuai.utils.ViewUtils;

public class MyGestureImageView extends GestureImageView {
    private Paint mPaint;
    private boolean isDrawGridLine = false;

    public MyGestureImageView(Context context) {
        super(context);
        init();
    }

    public MyGestureImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public MyGestureImageView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    private void init() {
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setStrokeWidth(1);
        mPaint.setColor(Color.WHITE);
    }

    public void setDrawGridLine(boolean drawGridLine) {
        isDrawGridLine = drawGridLine;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        Log.e("zs", "ondraw");
        //绘制九宫格
        if (isDrawGridLine) {
            float imageW = (getController().getSettings().getImageW() * getController().getState().getZoom());
            float imageH = (getController().getSettings().getImageH() * getController().getState().getZoom());
            float sw = ViewUtils.getScreenWidthPx(getContext());
            int width = (int) (imageW > sw ? sw : imageW);
            int height = (int) (imageH > sw ? sw : imageH);

            int offsetW = (getWidth() - width) / 2;
            int offsetH = (getHeight() - height) / 2;
            //竖线
            canvas.drawLine(width / 3 + offsetW, offsetH, width / 3 + offsetW, height + offsetH, mPaint);
            canvas.drawLine(width / 3 * 2 + offsetW, offsetH, width / 3 * 2 + offsetW, height + offsetH, mPaint);
            //横线
            canvas.drawLine(offsetW, height / 3 + offsetH, width + offsetW, height / 3 + offsetH, mPaint);
            canvas.drawLine(offsetW, height / 3 * 2 + offsetH, width + offsetW, height / 3 * 2 + offsetH, mPaint);
        }
    }
}
