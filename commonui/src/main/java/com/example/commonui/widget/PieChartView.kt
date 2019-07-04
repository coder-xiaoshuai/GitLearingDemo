package com.example.commonui.widget

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.RectF
import android.util.AttributeSet
import android.util.Log
import android.view.MotionEvent
import android.view.View
import com.example.commonui.widget.util.sumToIndex


class PieChartView @JvmOverloads constructor(context: Context?, attrs: AttributeSet? = null, defStyleAttr: Int = 0) : View(context, attrs, defStyleAttr) {
    private var colorArray = arrayOf(Color.RED, Color.YELLOW, Color.CYAN, Color.GREEN, Color.DKGRAY)
    private var angleArrayDefault = arrayOf(0.35f, 0.20f, 0.15f, 0.05f, 0.25f)
    private var mPaint = Paint(Paint.ANTI_ALIAS_FLAG)

    companion object {
        private const val DEFAULT_SIZE = 200
        private const val RADIUS_DEFAULT_OFFSET = 100f
    }

    init {
        context?.let {
            val typedArray = context!!.obtainStyledAttributes(attrs, com.example.commonui.R.styleable.PieChartView)
            typedArray.recycle()
        }
        mPaint.isDither = true

    }


    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        if (canvas == null) {
            return
        }
        var isNeedSpecialHandle = false
        angleArrayDefault.forEachIndexed { index, angle ->

            var rectF = RectF(RADIUS_DEFAULT_OFFSET, RADIUS_DEFAULT_OFFSET, width.toFloat() - RADIUS_DEFAULT_OFFSET, height.toFloat() - RADIUS_DEFAULT_OFFSET)
            mPaint.color = colorArray[index]
            if (index == 0) {
                rectF = RectF(RADIUS_DEFAULT_OFFSET - 50 * Math.sin(angle * 360f / 2.0).toFloat(), RADIUS_DEFAULT_OFFSET - 50 * Math.sin(angle * 360f / 2.0).toFloat(), width.toFloat() - RADIUS_DEFAULT_OFFSET + 50 * Math.sin(angle * 360f / 2.0).toFloat(), height.toFloat() - RADIUS_DEFAULT_OFFSET + 50 * Math.sin(angle * 360f / 2.0).toFloat())
            }
            canvas.drawArc(rectF, angleArrayDefault.sumToIndex(index - 1) * 360f, angle * 360f, true, mPaint)
            if (isNeedSpecialHandle) {
                canvas.restore()
                isNeedSpecialHandle = false
            }
        }
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        val widthSpecMode = View.MeasureSpec.getMode(widthMeasureSpec)
        val widthSpecSize = View.MeasureSpec.getSize(widthMeasureSpec)
        val heightSpecMode = View.MeasureSpec.getMode(heightMeasureSpec)
        val heightSpecSize = View.MeasureSpec.getSize(heightMeasureSpec)

        if (widthSpecMode == View.MeasureSpec.AT_MOST && heightSpecMode == View.MeasureSpec.AT_MOST) {
            setMeasuredDimension(DEFAULT_SIZE, DEFAULT_SIZE)
        } else if (widthSpecMode == View.MeasureSpec.AT_MOST) {
            setMeasuredDimension(DEFAULT_SIZE, heightSpecSize)
        } else if (heightSpecMode == View.MeasureSpec.AT_MOST) {
            setMeasuredDimension(widthSpecSize, DEFAULT_SIZE)
        }
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        if (event == null) {
            return false
        }
        when (event.action) {
            MotionEvent.ACTION_DOWN -> {
                val onTouchX = event.x
                val onTouchY = event.y
                if (isInCircle(onTouchX, onTouchY)) {
                    Log.e("zs", "获取的角度" + getAngle(onTouchX, onTouchY))
                }
            }

            MotionEvent.ACTION_UP, MotionEvent.ACTION_CANCEL -> {

            }
        }
        return true
    }

    private fun isInCircle(x: Float, y: Float): Boolean {
        if (((x - width / 2) * (x - width / 2) + (y - height / 2) * (y - height / 2)) < (width - RADIUS_DEFAULT_OFFSET) / 2 * (width - RADIUS_DEFAULT_OFFSET) / 2) {
            return true
        }
        return false
    }

    private fun getClickIndex(x: Float, y: Float): Int {
        return 0
    }


    fun getAngle(x: Float, y: Float): Float {
        //得到角度
        var touchAngle = Math.toDegrees(Math.atan2((y - height / 2).toDouble(), (x - width / 2).toDouble()))
        if (touchAngle < 0) {
            touchAngle += 360.0
        }
        return touchAngle.toFloat()
    }
}