package com.example.commonui.widget

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.os.Handler
import android.os.Message
import android.util.AttributeSet
import android.view.View
import java.util.*

/**
 * Created by zhang on 2017/12/20.
 */
class ClockView(context: Context?, attrs: AttributeSet? = null, defStyleAttr: Int = 0) : View(context, attrs, defStyleAttr) {

    companion object {
        private const val DEFAULT_WIDTH = 200 //默认宽度
    }

    private lateinit var mBlackPaint: Paint//黑色画笔
    private lateinit var mRedPaint: Paint //红色画笔
    private lateinit var mBlackPaint2: Paint//黑色画笔
    private lateinit var mTextPaint: Paint
    private var hour: Int? = null
    private var minute: Int? = null
    private var second: Int? = null
    private val textArray = arrayOf("12", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11")
    private var refreshThread: Thread? = null
    private var mHandler = @SuppressLint("HandlerLeak")
    object : Handler() {
        override fun handleMessage(msg: Message?) {
            super.handleMessage(msg)
            when (msg?.what) {
                0 -> {
                    invalidate()
                }
            }

        }
    }

    init {
        initPaints()
    }

    /**
     * 初始化画笔
     */
    private fun initPaints() {
        mBlackPaint = Paint()
        with(mBlackPaint) {
            color = Color.BLACK
            strokeWidth = 5f
            isAntiAlias = true
            style = Paint.Style.STROKE
        }
        //用于画表心
        mBlackPaint2 = Paint()
        with(mBlackPaint2) {
            color = Color.BLACK
            isAntiAlias = true
            style = Paint.Style.FILL
        }
        mRedPaint = Paint()
        with(mRedPaint) {
            color = Color.RED
            strokeWidth = 5f
            isAntiAlias = true
        }

        mTextPaint = Paint()
        with(mTextPaint) {
            color = Color.BLACK
            textSize = 30f
            isAntiAlias = true
        }
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        //获取当前时间
        getCurrentTime()

        //先画最外层的圆圈
        drawOuterCircle(canvas)

        //画刻度
        drawScale(canvas)

        //绘制文字
        drawTimeText(canvas)

        //绘制表针
        drawHand(canvas)

        //绘制表心
        drawCenter(canvas)
    }

    private fun getCurrentTime() {
        val calendar = Calendar.getInstance()
        hour = calendar.get(Calendar.HOUR)
        minute = calendar.get(Calendar.MINUTE)
        second = calendar.get(Calendar.SECOND)
    }

    private fun drawOuterCircle(canvas: Canvas?) {
        mBlackPaint.strokeWidth = 5f
        canvas?.drawCircle(measuredWidth / 2.toFloat(), measuredHeight / 2.toFloat(), (measuredWidth / 2 - 5).toFloat(), mBlackPaint)
    }

    private fun drawCenter(canvas: Canvas?) {
        canvas?.drawCircle(measuredWidth / 2.toFloat(), measuredHeight / 2.toFloat(), 20f, mBlackPaint2)
    }

    private fun drawHand(canvas: Canvas?) {
        drawSecond(canvas, mRedPaint)
        mBlackPaint.strokeWidth = 10f
        drawMinute(canvas, mBlackPaint)
        mBlackPaint.strokeWidth = 15f
        drawHour(canvas, mBlackPaint)
    }

    private fun drawTimeText(canvas: Canvas?) {
        val textR = (measuredWidth / 2 - 50).toFloat()//文字构成的圆的半径
        for (i in 0..11) {
            //绘制文字的起始坐标
            val startX = (measuredWidth / 2 + textR * Math.sin(Math.PI / 6 * i) - mTextPaint.measureText(textArray[i]) / 2).toFloat()
            val startY = (measuredHeight / 2 - textR * Math.cos(Math.PI / 6 * i) + mTextPaint.measureText(textArray[i]) / 2).toFloat()
            canvas?.drawText(textArray[i], startX, startY, mTextPaint)
        }
    }

    private fun drawScale(canvas: Canvas?) {
        var scaleLength: Float?
        canvas?.save()
        //0..59代表[0,59]
        for (i in 0..59) {
            if (i % 5 == 0) {
                //大刻度
                mBlackPaint.strokeWidth = 5f
                scaleLength = 20f
            } else {
                //小刻度
                mBlackPaint.strokeWidth = 3f
                scaleLength = 10f
            }
            canvas?.drawLine(measuredWidth / 2.toFloat(), 5f, measuredWidth / 2.toFloat(), (5 + scaleLength), mBlackPaint)
            canvas?.rotate(360 / 60.toFloat(), measuredWidth / 2.toFloat(), measuredHeight / 2.toFloat())
        }
        //恢复原来状态
        canvas?.restore()
    }

    /**
     * 绘制秒针
     */
    private fun drawSecond(canvas: Canvas?, paint: Paint?) {
        //秒针长半径 (表针会穿过表心 所以需要根据两个半径计算起始和结束半径)
        val longR = measuredWidth / 2 - 60
        val shortR = 60
        val startX = (measuredWidth / 2 - shortR * Math.sin(second!!.times(Math.PI / 30))).toFloat()
        val startY = (measuredWidth / 2 + shortR * Math.cos(second!!.times(Math.PI / 30))).toFloat()
        val endX = (measuredWidth / 2 + longR * Math.sin(second!!.times(Math.PI / 30))).toFloat()
        val endY = (measuredWidth / 2 - longR * Math.cos(second!!.times(Math.PI / 30))).toFloat()
        canvas?.drawLine(startX, startY, endX, endY, paint)
    }

    /**
     * 绘制分针
     */
    private fun drawMinute(canvas: Canvas?, paint: Paint?) {
        //半径比秒针小一点
        val longR = measuredWidth / 2 - 90
        val shortR = 50
        val startX = (measuredWidth / 2 - shortR * Math.sin(minute!!.times(Math.PI / 30))).toFloat()
        val startY = (measuredWidth / 2 + shortR * Math.cos(minute!!.times(Math.PI / 30))).toFloat()
        val endX = (measuredWidth / 2 + longR * Math.sin(minute!!.times(Math.PI / 30))).toFloat()
        val endY = (measuredWidth / 2 - longR * Math.cos(minute!!.times(Math.PI / 30))).toFloat()
        canvas?.drawLine(startX, startY, endX, endY, paint)
    }


    /**
     * 绘制时针
     */
    private fun drawHour(canvas: Canvas?, paint: Paint?) {
        //半径比秒针小一点
        val longR = measuredWidth / 2 - 120
        val shortR = 40
        val startX = (measuredWidth / 2 - shortR * Math.sin(hour!!.times(Math.PI / 6))).toFloat()
        val startY = (measuredWidth / 2 + shortR * Math.cos(hour!!.times(Math.PI / 6))).toFloat()
        val endX = (measuredWidth / 2 + longR * Math.sin(hour!!.times(Math.PI / 6))).toFloat()
        val endY = (measuredWidth / 2 - longR * Math.cos(hour!!.times(Math.PI / 6))).toFloat()
        canvas?.drawLine(startX, startY, endX, endY, paint)
    }

    /**
     * 进行测量
     */
    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        val widthSpecMode = MeasureSpec.getMode(widthMeasureSpec)
        val widthSpecSize = MeasureSpec.getSize(widthMeasureSpec)
        val heightSpecMode = MeasureSpec.getMode(heightMeasureSpec)
        val heightSpecSize = MeasureSpec.getSize(heightMeasureSpec)
        val result = if (widthSpecMode == MeasureSpec.AT_MOST && heightSpecMode == MeasureSpec.AT_MOST) {
            DEFAULT_WIDTH
        } else {
            Math.min(widthSpecSize, heightSpecSize)
        }

        setMeasuredDimension(result, result)
    }

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        //启动线程 刷新界面
        refreshThread = Thread(Runnable {
            while (true) {
                try {
                    Thread.sleep(1000)
                    mHandler.sendEmptyMessage(0)
                } catch (e: InterruptedException) {
                    break
                }
            }
        })
        refreshThread?.start()
    }

    override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()
        handler.removeCallbacks(null)
        //中断线程
        refreshThread?.interrupt()
    }
}

