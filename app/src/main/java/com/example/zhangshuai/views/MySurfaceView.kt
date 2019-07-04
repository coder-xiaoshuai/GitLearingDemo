package com.example.zhangshuai.views

import android.content.Context
import android.graphics.Canvas
import android.util.AttributeSet
import android.view.SurfaceHolder
import android.view.SurfaceView
import kotlin.properties.Delegates

class MySurfaceView @JvmOverloads constructor(context: Context?, attrs: AttributeSet? = null, defStyleAttr: Int = 0) : SurfaceView(context, attrs, defStyleAttr), SurfaceHolder.Callback, Runnable {

    private var mHolder: SurfaceHolder by Delegates.notNull()
    private var mCanvas: Canvas by Delegates.notNull()
    private var mIsDrawing = false


    init {
        initView()
    }


    private fun initView() {
        mHolder = holder
        mHolder.addCallback(this)
        isFocusable = true
        isFocusableInTouchMode = true
    }

    override fun surfaceChanged(holder: SurfaceHolder?, format: Int, width: Int, height: Int) {

    }

    override fun surfaceDestroyed(holder: SurfaceHolder?) {
        mIsDrawing = false
    }

    override fun surfaceCreated(holder: SurfaceHolder?) {
        mIsDrawing = true
        Thread(this).start()
    }

    override fun run() {
        draw()
    }

    /**
     * 绘图操作
     */
    private fun draw() {
        try {
            mCanvas = mHolder.lockCanvas()
            //draw sth绘制过程
        } catch (e: Exception) {

        } finally {
            if (mCanvas != null) {
                mHolder.unlockCanvasAndPost(mCanvas)
            }
        }

    }
}