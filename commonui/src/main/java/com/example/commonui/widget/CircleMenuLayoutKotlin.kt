package com.example.commonui.widget

import android.content.Context
import android.util.AttributeSet
import android.util.DisplayMetrics
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager


class CircleMenuLayoutKotlin(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0) : ViewGroup(context, attrs, defStyleAttr) {

    companion object {
        //静态变量
        val RADIO_DEFAULT_CHILD_DIMENSION = 1 / 4F
        val RADIO_DEFAULT_CENTERITEM_DIMENSION = 1 / 3F
        val RADIO_PADDING_LAYOUT = 1 / 12f
        val FLINGABLE_VALUE = 300
        val NOCLICK_VALUE = 3


    }

    //非静态变量
    var mRadius = 0
    var mFlingableValue = FLINGABLE_VALUE
    var mPadding = 0f
    var mStartAngle: Double? = 0.0 //默认是double
    var mItemTexts: Array<String>? = null
    var mItemImgs: Array<Int>? = null
    var mMenuItemCount = 0
    var mTmpAngle = 0f
    var mDownTime = 0L
    var isFling = false
    var mMenuItemLayoutId = 0
    var mLastX = 0f
    var mLastY = 0f

    init {
        //无视padding
        setPadding(0, 0, 0, 0)
    }

    override fun onLayout(changed: Boolean, l: Int, t: Int, r: Int, b: Int) {
        var layoutRadius = mRadius
        //laying out the child views

        var left = 0
        var top = 0
        val cWidth = layoutRadius.times(RADIO_DEFAULT_CHILD_DIMENSION)
        val angleDelay = 360.div(childCount.minus(1))
        for (i in 0..childCount) {
            val child = getChildAt(i)
            if (child.id == 0) {
                continue
            }
            if (child.visibility == View.GONE) {
                continue
            }
            mStartAngle = mStartAngle?.rem(360)
            //计算，中心点到menu item中心的距离
            var tmp = layoutRadius.div(2f).minus(cWidth.div(2)).minus(mPadding)
            left = layoutRadius / 2 + Math.round(tmp * Math.cos(Math.toRadians(mStartAngle!!)) - 1 / 2f * cWidth) as Int
            // tmp sina 即menu item的纵坐标
            top = layoutRadius / 2 + Math.round(tmp * Math.sin(Math.toRadians(mStartAngle!!)) - 1 / 2f * cWidth) as Int

            child.layout(left, top, left.plus(cWidth).toInt(), top.plus(cWidth).toInt())
            mStartAngle = mStartAngle!!.plus(angleDelay)
        }

        //找到中心的view，如果存在设置onclick事件
        val cView = findViewById<View>(0)
        cView.setOnClickListener {
            mOnMenuItemClickListener?.itemCenterClick(it)
        }
        // 设置center item位置
        if (cView != null) {
            val cl = layoutRadius / 2 - cView.measuredWidth / 2
            val cr = cl + cView.measuredWidth
            cView?.layout(cl, cl, cr, cr)
        }

    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        var resWidth = 0
        var resHeight = 0

        val width = MeasureSpec.getSize(widthMeasureSpec)
        val widthMode = MeasureSpec.getMode(widthMeasureSpec)

        val height = MeasureSpec.getSize(heightMeasureSpec)
        val heightMode = MeasureSpec.getMode(heightMeasureSpec)

        if (widthMode != MeasureSpec.EXACTLY || heightMode != MeasureSpec.EXACTLY) {
            //主要设置为背景图的高度
            resWidth = suggestedMinimumWidth
            //如果未设置背景图片，则设置为屏幕宽高的默认值
            resWidth = if (resWidth == 0) getDefaultWidth() else resWidth

            resHeight = suggestedMinimumHeight
            resHeight = if (resHeight == 0) getDefaultWidth() else resHeight
        } else {
            //如果都设置为精确值，则直接取最小值
            resWidth = Math.min(resWidth, resHeight)
            resHeight = resWidth
        }

        setMeasuredDimension(resWidth, resHeight)

        //获得半径
        mRadius = Math.max(measuredWidth, measuredHeight)

        //menu item数量
        var count = childCount
        var childSize = mRadius.times(RADIO_DEFAULT_CHILD_DIMENSION).toInt()
        var childMode = MeasureSpec.EXACTLY
        //迭代测量
        for (i in 0..count) {
            val child = getChildAt(i)
            if (child.visibility == View.GONE) {
                continue;
            }
            //计算menu item的尺寸 以及和设置好的模式 去对item进行测量
            var makeMeasureSpec = -1
            if (child.id == 0) {
                makeMeasureSpec = MeasureSpec.makeMeasureSpec(mRadius * RADIO_DEFAULT_CENTERITEM_DIMENSION.toInt(), childMode)
            } else {
                makeMeasureSpec = MeasureSpec.makeMeasureSpec(childSize, childMode)
            }
            child.measure(makeMeasureSpec, makeMeasureSpec)
        }

        mPadding = RADIO_PADDING_LAYOUT * mRadius

    }

    var mOnMenuItemClickListener: OnMenuItemClickListener? = null
    fun setOnMenuItemClickListener(onMenuItemClickListener: OnMenuItemClickListener) {
        this.mOnMenuItemClickListener = onMenuItemClickListener
    }

    interface OnMenuItemClickListener {
        fun itemClick(view: View, postion: Int)
        fun itemCenterClick(view: View)
    }


    fun getDefaultWidth(): Int {
        val wm = context.getSystemService(Context.WINDOW_SERVICE) as WindowManager
        val outMetrics = DisplayMetrics()
        wm.defaultDisplay.getMetrics(outMetrics)
        return Math.min(outMetrics.widthPixels, outMetrics.heightPixels)
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        return true
    }

    fun getAngle(xTouch: Float, yTouch: Float): Float {
        var x = xTouch.minus(mRadius.div(2.0))
        var y = yTouch.minus(mRadius.div(2.0))
        return Math.asin(y.div(Math.hypot(x, y))).times(180).div(Math.PI).toFloat()
    }

    fun getQuadrant(x: Float, y: Float): Int {
        var tmpX = (x - mRadius / 2).toInt()
        var tmpY = (y - mRadius / 2).toInt()
        if (tmpX >= 0) {
            return if (tmpY >= 0) 4 else 1
        } else {
            return if (tmpY >= 0) 3 else 2
        }
    }

    fun setMeunItemLayoutId(mMenuItemLayoutId: Int) {
        this.mMenuItemLayoutId = mMenuItemLayoutId
    }

    fun setFlingableValue(mFlingableValue: Int) {
        this.mFlingableValue = mFlingableValue
    }

    fun setPadding(padding: Float) {
        this.mPadding = mPadding
    }

    inner class AutoFlingRunnable(velocity: Float) : Runnable {
        var angelPerSecond = 0f

        init {
            this.angelPerSecond = velocity
        }

        override fun run() {
            if (Math.abs(angelPerSecond).toInt() < 20) {
                isFling = false
                return
            }
            isFling = true
            //不断改变mStartAngle，让其滚动，/30为了避免滚动太快
            mStartAngle = mStartAngle?.plus(angelPerSecond.div(30))
            //逐渐减小这个值
            angelPerSecond = angelPerSecond.div(1.0666f)
            postDelayed(this, 30)
            requestLayout()
        }
    }
}