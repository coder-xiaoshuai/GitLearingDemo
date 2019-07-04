package com.example.commonui.layoutmanger

import android.graphics.PointF
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearSmoothScroller
import androidx.recyclerview.widget.OrientationHelper
import androidx.recyclerview.widget.RecyclerView

class TestLayoutManger02 : RecyclerView.LayoutManager() {

    private var helper: OrientationHelper? = null
    private var mPendingScrollPosition = 0
    /**
     * 这个方法是必须实现的，那么实现它到底有什么作用呢？简单的解释下，假设我们平时调用LayoutInflate.inflate(resource,null,false)第二个参数
     * 传的null或者直接使用View.inflate()去填充View的时候那么填充的View是没有布局参数的，那么当我们的Recyclerview去addView()时就会进行判断，
     * 如果childView的布局参数为null就是调用这个方法去生成一个默认的布局参数
     */
    override fun generateDefaultLayoutParams(): RecyclerView.LayoutParams {
        return RecyclerView.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT)
    }

    /**
     * 有点类似ViewGroup的onLayout方法
     */
    override fun onLayoutChildren(recycler: RecyclerView.Recycler?, state: RecyclerView.State?) {
        super.onLayoutChildren(recycler, state)
        if (itemCount == 0) {
            recycler?.let {
                detachAndScrapAttachedViews(it)
            }
            return
        }

        if (childCount == 0 && state != null && state.isPreLayout) {
            return
        }

        //初始化OrientationHelper
        ensureStatus()

        var offset = 0
        //计算 Recyclerview 可用布局宽度 具体实现 mLayoutManager.getWidth() - mLayoutManager.getPaddingLeft()- mLayoutManager.getPaddingRight();
        var mAvailable = helper!!.totalSpace

        //调用notifyDataSetChanged 才有 getChildCount()!=0
        if (childCount != 0) {
            //得到第一个可见的childView
            val firstView = getChildAt(0)
            //得到第一个可见childView左边的位置
            offset = helper!!.getDecoratedStart(firstView)
            //获取第一个可见childView在Adapter中的position
            mPendingScrollPosition = getPosition(firstView!!)
            //offset的值为负数，在可见区域的左边，那么当重新布局时得考虑正偏移
            mAvailable += Math.abs(offset)
        }

        //移除所有的view
        recycler?.let {
            detachAndScrapAttachedViews(it)
        }

        //将可见区域的childView layout出来
        layoutScrap(recycler!!, state!!, offset, mAvailable)

    }

    /**
     * 是否可以垂直滚动
     */
    override fun canScrollVertically(): Boolean {
        return super.canScrollVertically()
    }


    override fun scrollVerticallyBy(dy: Int, recycler: RecyclerView.Recycler?, state: RecyclerView.State?): Int {
        return super.scrollVerticallyBy(dy, recycler, state)
    }

    override fun canScrollHorizontally(): Boolean {
        return true
    }

    override fun scrollHorizontallyBy(dx: Int, recycler: RecyclerView.Recycler?, state: RecyclerView.State?): Int {

        recycler?.let {
            //回收不可见childView
            recyclerUnVisiableView(recycler, dx)
            //将新出现的childView layout出来
            val willScroll = fillChild(recycler, dx, state!!)
            //水平方向移动childView
            offsetChildrenHorizontal(-willScroll)
            return willScroll
        }

        return super.scrollHorizontallyBy(dx, recycler, state)
    }

    private fun ensureStatus() {
        if (helper == null) {
            helper = OrientationHelper.createHorizontalHelper(this)
        }
    }


    private fun layoutScrap(recycler: RecyclerView.Recycler, state: RecyclerView.State, offset: Int, mAvailable: Int) {
        var mAvailable = mAvailable
        var offset = offset
        for (i in mPendingScrollPosition..state.itemCount) {
            //可用布局宽度不足，跳出循环
            if (mAvailable <= 0) {
                break
            }
            //在右边添加新的childView
            val childWidth = layoutScrapRight(recycler, i, offset)
            mAvailable -= childWidth
            offset += childWidth
        }
    }

    private fun layoutScrapRight(recycler: RecyclerView.Recycler, position: Int, offset: Int): Int {
        return layoutScrap(recycler, position, offset, ADD_RIGHT)
    }

    private fun layoutScrapLeft(recycler: RecyclerView.Recycler, position: Int, offset: Int): Int {
        return layoutScrap(recycler, position, offset, ADD_LEFT)
    }

    private fun layoutScrap(recycler: RecyclerView.Recycler, position: Int, offset: Int, direction: Int): Int {
        //从recycler 中取到将要出现的childView
        val childPosition = recycler.getViewForPosition(position)
        if (direction == ADD_RIGHT) {
            addView(childPosition)
        } else {
            addView(childPosition, 0)
        }
        //计算childView的大小
        measureChildWithMargins(childPosition, 0, 0)
        val childWidth = getDecoratedMeasuredWidth(childPosition)
        val childHeight = getDecoratedMeasuredHeight(childPosition)
        if (direction == ADD_RIGHT) {
            //layout childView
            layoutDecorated(childPosition, offset, 0, offset + childWidth, childHeight)
        } else {
            layoutDecorated(childPosition, offset - childWidth, 0, offset, childHeight)
        }
        return childWidth
    }

    private fun fillChild(recycler: RecyclerView.Recycler, dx: Int, state: RecyclerView.State): Int {
        if (dx > 0) {//RecyclerView从右往左滑动时
            //得到最后一个可见childView
            val lastView = getChildAt(childCount - 1)
            //得到将显示的childView 在adapter 中的position
            val position = getPosition(lastView!!) + 1
            //得到最后一个课件childView右边的便宜
            var offset = helper!!.getDecoratedEnd(lastView)
            //判断是否有足够的空间
            if (offset - dx < width - paddingRight) {
                //item足够
                if (position < state.itemCount) {
                    layoutScrapRight(recycler, position, offset)
                } else {
                    //item不足 返回新的可滚动的宽度
                    return offset - width + paddingRight
                }
            }
        } else {//RecyclerView从左往右滑动时
            //得到第一个可见childView
            val firstView = getChildAt(0)
            //得到将显示的childView 在adapter 中的position
            val position = getPosition(firstView!!) - 1
            //得到第一个可见childView左边的偏移
            var offset = helper!!.getDecoratedStart(firstView)
            //判断是否有足够的空间
            if (offset - dx > paddingLeft) {
                //item 足够
                if (position >= 0) {
                    layoutScrapLeft(recycler, position, offset)
                } else {
                    //item不足 返回新的可滚动的宽度
                    return offset - paddingLeft
                }
            }
        }
        return dx
    }


    private fun recyclerUnVisiableView(recycler: RecyclerView.Recycler, dx: Int) {
        for (i in 0..childCount) {
            val child = getChildAt(i)
            if (dx > 0) {//RecyclerView从右往左滑动时
                //将左边小时的childView 回收掉
                if (helper!!.getDecoratedEnd(child) - dx < paddingLeft) {
                    removeAndRecycleView(child!!, recycler)
                    break
                }
            } else {//RecyclerView从左往右滑动时
                //将右边的childView 回收掉
                if (helper!!.getDecoratedStart(child) - dx > width - paddingRight) {
                    removeAndRecycleView(child!!, recycler)
                }
            }
        }
    }

    /**
     * 滑动到指定位置，调用该方法后，会调用onLayoutChildren
     */
    override fun scrollToPosition(position: Int) {
        super.scrollToPosition(position)
        mPendingScrollPosition = position
        requestLayout()
    }

    override fun smoothScrollToPosition(recyclerView: RecyclerView?, state: RecyclerView.State?, position: Int) {
        val linearSmoothScroller = object : LinearSmoothScroller(recyclerView!!.context) {
            override fun computeScrollVectorForPosition(targetPosition: Int): PointF? {
                if (childCount == 0) {
                    return null
                }
                val firstChildPos = getPosition(getChildAt(0)!!)
                val direction = if (targetPosition < firstChildPos) -1 else 1
                return PointF(direction.toFloat(), 0f)
            }
        }
        linearSmoothScroller.targetPosition = position
        startSmoothScroll(linearSmoothScroller)
    }

    companion object {
        private const val ADD_RIGHT = 1 //RecyclerView从右往左滑动时，新出现的child添加在右边
        private const val ADD_LEFT = -1  //RecyclerView从左往右滑动时，新出现的child添加在左边
    }
}