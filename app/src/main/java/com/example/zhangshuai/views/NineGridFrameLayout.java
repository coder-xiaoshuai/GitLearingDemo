package com.example.zhangshuai.views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.widget.FrameLayout;

import com.example.zhangshuai.utils.ViewUtils;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class NineGridFrameLayout extends FrameLayout {
    private Paint mPaint;

    public NineGridFrameLayout(@NonNull Context context) {
        super(context);
        init();
    }

    public NineGridFrameLayout(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public NineGridFrameLayout(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setStrokeWidth(ViewUtils.dpToPx(getContext(), 0.1f));
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
        canvas.drawLine(0, getHeight() / 3 * 2, getWidth(), getHeight() / 3 * 2, mPaint);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }
}
