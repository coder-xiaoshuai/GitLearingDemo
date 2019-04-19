package com.example.commonui.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class NineGridLayout extends RelativeLayout {
    private Paint mPaint;
    private static float DEFAULT_STROKE_WIDTH = 30f;

    public boolean isShowGridLine() {
        return showGridLine;
    }

    public void setShowGridLine(boolean showGridLine) {
        this.showGridLine = showGridLine;
    }

    private boolean showGridLine;

    public NineGridLayout(@NonNull Context context) {
        super(context);
        init();
    }

    public NineGridLayout(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public NineGridLayout(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
        setClickable(true);
    }

    private void init() {
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setStrokeWidth(DEFAULT_STROKE_WIDTH);
        mPaint.setColor(Color.WHITE);
    }

    @Override
    protected void dispatchDraw(Canvas canvas) {
        super.dispatchDraw(canvas);
        Log.e("zs", "dispatchDraw");
        if (showGridLine) {
            float remainWidth = getWidth() - DEFAULT_STROKE_WIDTH * 2;
            float remainHeight = getHeight() - DEFAULT_STROKE_WIDTH * 2;
            //竖线
            canvas.drawLine(remainWidth / 3 + DEFAULT_STROKE_WIDTH / 2, 0, remainWidth / 3 + DEFAULT_STROKE_WIDTH / 2,
                getHeight(), mPaint);

            canvas.drawLine(remainWidth / 3 * 2 + DEFAULT_STROKE_WIDTH / 2 + DEFAULT_STROKE_WIDTH, 0,
                remainWidth / 3 * 2 + DEFAULT_STROKE_WIDTH / 2 + DEFAULT_STROKE_WIDTH, getHeight(), mPaint);

            //横线
            canvas.drawLine(0, remainHeight / 3 + DEFAULT_STROKE_WIDTH / 2, getWidth(),
                remainHeight / 3 + DEFAULT_STROKE_WIDTH / 2, mPaint);
            canvas.drawLine(0, remainHeight / 3 * 2 + DEFAULT_STROKE_WIDTH / 2 + DEFAULT_STROKE_WIDTH, getWidth(),
                remainHeight / 3 * 2 + DEFAULT_STROKE_WIDTH / 2 + DEFAULT_STROKE_WIDTH, mPaint);
        }
    }

    /**
     * 将dip或dp值转换为px值
     */
    public static int dpToPx(Context context, float dipValue) {
        if (context == null) {
            return 0;
        }
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dipValue * scale + 0.5f);
    }
}
