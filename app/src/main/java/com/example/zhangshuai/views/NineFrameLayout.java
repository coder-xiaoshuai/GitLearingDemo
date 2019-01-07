package com.example.zhangshuai.views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.FrameLayout;

public class NineFrameLayout extends FrameLayout {
    private Paint mPaint;

    public NineFrameLayout(@NonNull Context context) {
        super(context);
        init();
    }

    public NineFrameLayout(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public NineFrameLayout(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setStrokeWidth(0.5f);
        mPaint.setColor(Color.WHITE);
    }

    @Override
    protected void dispatchDraw(Canvas canvas) {
        super.dispatchDraw(canvas);
        //竖线
        canvas.drawLine(getWidth() / 3, 0, getWidth() / 3, getHeight(), mPaint);
        canvas.drawLine(getWidth() / 3 * 2, 0, getWidth() / 3 * 2, getHeight(), mPaint);
        //横线
        canvas.drawLine(0, getHeight() / 3, getWidth(), getHeight() / 3, mPaint);
        canvas.drawLine(0, getHeight() / 3*2, getWidth(), getHeight() / 3*2, mPaint);
    }
}
