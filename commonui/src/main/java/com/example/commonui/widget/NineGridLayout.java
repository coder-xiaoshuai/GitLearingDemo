package com.example.commonui.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.RelativeLayout;

public class NineGridLayout extends RelativeLayout {
    private Paint mPaint;

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
        mPaint.setStrokeWidth(dpToPx(getContext(), 1f));
        mPaint.setColor(Color.WHITE);
    }

    @Override
    protected void dispatchDraw(Canvas canvas) {
        super.dispatchDraw(canvas);
        Log.e("zs", "dispatchDraw");
        if (showGridLine) {
            //竖线
            canvas.drawLine(getWidth() / 3, 0, getWidth() / 3, getHeight(), mPaint);
            canvas.drawLine(getWidth() / 3 * 2, 0, getWidth() / 3 * 2, getHeight(), mPaint);
            //横线
            canvas.drawLine(0, getHeight() / 3, getWidth(), getHeight() / 3, mPaint);
            canvas.drawLine(0, getHeight() / 3 * 2, getWidth(), getHeight() / 3 * 2, mPaint);
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
