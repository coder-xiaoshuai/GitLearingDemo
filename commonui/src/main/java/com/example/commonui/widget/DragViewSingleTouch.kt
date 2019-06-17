package com.example.commonui.widget

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import com.example.commonui.R

class DragViewSingleTouch @JvmOverloads constructor(context: Context?, attrs: AttributeSet? = null, defStyleAttr: Int = 0) : View(context, attrs, defStyleAttr) {
    var mBitmap: Bitmap? = null
    var mBitmapRectF: RectF? = null
    var mBitmapMatrix: Matrix? = null
    var canDrag = false
    var lastPoint = PointF(0f, 0f)
    var mDefaultPaint: Paint? = null

    init {
        mDefaultPaint = Paint(Paint.ANTI_ALIAS_FLAG.or(Paint.DITHER_FLAG))
        //调整图片大小
        var options = BitmapFactory.Options()
        options.outWidth = 960 / 2
        options.outHeight = 800 / 2

        mBitmap = BitmapFactory.decodeResource(this.resources, R.drawable.heart, options)
        mBitmapRectF = RectF(0f, 0f, mBitmap!!.width.toFloat(), mBitmap!!.height.toFloat())
        mBitmapMatrix = Matrix()
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        when (event?.actionMasked) {
            MotionEvent.ACTION_DOWN -> {
                //判断按下位置是否包含在图片区域内
                if (mBitmapRectF!!.contains(event.x, event.y)) {
                    canDrag = true
                    lastPoint.set(event.x, event.y)

                }
            }
            MotionEvent.ACTION_UP, MotionEvent.ACTION_CANCEL -> {
                canDrag = false
            }

            MotionEvent.ACTION_MOVE -> {
                if (canDrag) {
                    //移动图片
                    mBitmapMatrix?.postTranslate(event.x - lastPoint.x, event.y - lastPoint.y)
                    //更新上一次点位置
                    lastPoint.set(event.x, event.y)

                    //更新图片区域
                    mBitmapRectF = RectF(0f, 0f, mBitmap!!.width.toFloat(), mBitmap!!.height.toFloat())
                    mBitmapMatrix?.mapRect(mBitmapRectF)

                    invalidate()
                }
            }
        }
        return true
    }

    override fun onDraw(canvas: Canvas?) {
        canvas?.drawBitmap(mBitmap, mBitmapMatrix, mDefaultPaint)
    }
}