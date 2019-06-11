package com.example.commonui.widget

import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View

class EventListenerView @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0) : View(context, attrs, defStyleAttr) {

    companion object {
        const val CLICK_THRESHOLD = 10
        const val MOVE_THRESHOLD = 24
    }

    var eventListener: EventListener? = null
    var lastTouchX = 0
    var lastTouchY = 0
    var isOverClickThreshold = false

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        when (event?.action) {
            MotionEvent.ACTION_DOWN -> {
                eventListener?.onDown()
                lastTouchX = event.x.toInt()
                lastTouchY = event.y.toInt()
            }
            MotionEvent.ACTION_MOVE -> {
                eventListener?.onMove()
                var currentTouchX = event.x.toInt()
                var currentTouchY = event.y.toInt()
                var distanceX = currentTouchX.minus(lastTouchX)
                var distanceY = currentTouchY.minus(lastTouchY)
                if (Math.abs(distanceX) > CLICK_THRESHOLD && Math.abs(distanceY) > CLICK_THRESHOLD) {
                    isOverClickThreshold = true
                }
                if (Math.abs(distanceX) > MOVE_THRESHOLD && Math.abs(distanceX) > Math.abs(distanceY)) {
                    //水平滑动
                    if (distanceX > 0) {
                        eventListener?.rightSlide()
                    } else {
                        eventListener?.leftSlide()
                    }
                }

                if (Math.abs(distanceY) > MOVE_THRESHOLD && Math.abs(distanceY) > Math.abs(distanceX)) {
                    //垂直滑动
                    if (distanceY > 0) {
                        eventListener?.bottomSlide()
                    } else {
                        eventListener?.topSlide()
                    }
                }
            }
            MotionEvent.ACTION_UP, MotionEvent.ACTION_CANCEL -> {
                eventListener?.onUp()
                if (!isOverClickThreshold) {
                    eventListener?.onClick()
                }
            }

        }
        return true
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
    }

    interface EventListener {
        fun onDown()
        fun onMove()
        fun leftSlide()
        fun rightSlide()
        fun topSlide()
        fun bottomSlide()
        fun onClick()
        fun onUp()
    }
}