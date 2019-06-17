package com.example.commonui.widget

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.util.Log
import android.view.MotionEvent
import android.view.View
import com.example.commonui.R

class DragViewFinal @JvmOverloads constructor(context: Context?, attrs: AttributeSet? = null, defStyleAttr: Int = 0) : View(context, attrs, defStyleAttr) {
    companion object {
        const val TAG = "DragViewFinal"
        const val INVALID_POINTER = -1
    }

    var mBitmap: Bitmap? = null
    var mBitmapRectF: RectF? = null
    var mBitmapMatrix: Matrix? = null
    var canDrag = false
    var lastPoint = PointF(0f, 0f)
    var mDefaultPaint: Paint? = null
    var mActivePointerId = 0

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


    /**
     * 记录活动手指的id（activePointerId）,通过Id获取move事件的坐标
     * 在手指按下的时候，记录下activePointerId
     * 第二根手指按下的时候，更新activePointerId.（我们让第二根手指作为活动手指，忽略第一个手指的move）
     * 当其中一根手指抬起时，如果是第一根手指，那么不做处理，如果是第二根手指抬起，也就是活动手指抬起的话，将活动手指改回第一根
     */

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        val action = event?.actionMasked
        val actionIndex = event?.actionIndex

        when (action) {
            MotionEvent.ACTION_DOWN -> {
                //判断按下位置是否包含在图片区域内
                if (mBitmapRectF!!.contains(event.x, event.y)) {
                    mActivePointerId = event.getPointerId(0)
                    canDrag = true
                    lastPoint.set(event.x, event.y)

                }
            }
            MotionEvent.ACTION_POINTER_DOWN -> {
                //将新落下的那根手指作为活动手指
                mActivePointerId = event.getPointerId(actionIndex!!)
                lastPoint.set(event.getX(actionIndex), event.getY(actionIndex))
            }
            MotionEvent.ACTION_POINTER_UP -> {
                if (mActivePointerId == event.getPointerId(actionIndex!!)) {
                    //如果松开的是活动的手指，让还停留在屏幕上的最后一根手指作为活动手指
                    //This was our active pointer going up,Choose a new
                    // active pointer and adjust accordingly
                    // pointerIndex都是像0,1,2这样连续的

                    val newPointerIndex = if (actionIndex == 0) 1 else 0
                    mActivePointerId = event.getPointerId(newPointerIndex)
                    lastPoint.set(event.getX(newPointerIndex), event.getY(newPointerIndex))
                }
            }

            MotionEvent.ACTION_UP -> {
                //代表用户的最后一个手指离开了屏幕
                mActivePointerId = INVALID_POINTER
                canDrag = false
            }

            MotionEvent.ACTION_MOVE -> {
                Log.e("ACTION_MOVE", "Got ACTION_MOVE event but don't have an active pointer id.")
                if (mActivePointerId == INVALID_POINTER) {
                    return false
                }

                //如果存在第一个手指，且这个手指的落点在图片域内
                if (canDrag) {

                    val index = event.findPointerIndex(mActivePointerId)

                    //移动图片
                    mBitmapMatrix?.postTranslate(event.getX(index) - lastPoint.x, event.getY(index) - lastPoint.y)
                    //更新上一次点位置
                    lastPoint.set(event.getX(index), event.getY(index))

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