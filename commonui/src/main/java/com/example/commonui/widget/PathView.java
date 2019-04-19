package com.example.commonui.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

public class PathView extends View {
    private static final int DEFAULT_SIZE = 300;
    private Paint mPaint;
    private Paint mPaintCenter;

    public PathView(Context context) {
        super(context);
        init();
    }

    public PathView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public PathView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int radius = getWidth() / 4;
        canvas.drawCircle(getWidth() / 4, getHeight() / 4, radius, mPaint);
        canvas.drawCircle(getWidth() / 4 * 3, getHeight() / 4, radius, mPaint);
        canvas.drawCircle(getWidth() / 4, getHeight() / 4 * 3, radius, mPaint);
        canvas.drawCircle(getWidth() / 4 * 3, getHeight() / 4 * 3, radius, mPaint);
        canvas.drawCircle(getWidth() / 2, getHeight() / 2, radius, mPaintCenter);
    }

    private void init() {
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setColor(Color.BLUE);
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setStrokeWidth(3);
        mPaintCenter = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaintCenter.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SCREEN));
        mPaintCenter.setColor(Color.RED);
        mPaintCenter.setStyle(Paint.Style.FILL);

        setLayerType(View.LAYER_TYPE_SOFTWARE, null);
    }

    /**
     * 直接继承自view 必须重写onMeasure
     */
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int widthSpecMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSpecSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightSpecMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSpecSize = MeasureSpec.getSize(heightMeasureSpec);

        if (widthSpecMode == MeasureSpec.AT_MOST && heightSpecMode == MeasureSpec.AT_MOST) {
            setMeasuredDimension(DEFAULT_SIZE, DEFAULT_SIZE);
        } else if (widthSpecMode == MeasureSpec.AT_MOST) {
            setMeasuredDimension(DEFAULT_SIZE, heightSpecSize);
        } else if (heightSpecMode == MeasureSpec.AT_MOST) {
            setMeasuredDimension(widthSpecSize, DEFAULT_SIZE);
        }
    }
}
