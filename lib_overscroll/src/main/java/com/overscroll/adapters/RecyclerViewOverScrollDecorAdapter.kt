package com.overscroll.adapters

import android.graphics.Canvas
import android.view.View
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager


class RecyclerViewOverScrollDecorAdapter : IOverScrollDecoratorAdapter {
    var mRecyclerView: RecyclerView
    var mImpl: Impl
    var mIsItemTouchInEffect = false

    constructor(mRecyclerView: RecyclerView) {
        this.mRecyclerView = mRecyclerView
        val layoutManager = mRecyclerView.getLayoutManager()
        if (layoutManager is LinearLayoutManager || layoutManager is StaggeredGridLayoutManager) {
            val orientation = if (layoutManager is LinearLayoutManager)
                (layoutManager as LinearLayoutManager).orientation
            else
                (layoutManager as StaggeredGridLayoutManager).orientation

            if (orientation == LinearLayoutManager.HORIZONTAL) {
                mImpl = ImplHorizLayout()
            } else {
                mImpl = ImplVerticalLayout()
            }
        } else {
            throw IllegalArgumentException("Recycler views with custom layout managers are not supported by this adapter out of the box." + "Try implementing and providing an explicit 'impl' parameter to the other c'tors, or otherwise create a custom adapter subclass of your own.")
        }
    }

    constructor(recyclerView: RecyclerView, impl: Impl) {
        this.mRecyclerView = recyclerView
        this.mImpl = impl
    }

    constructor(recyclerView: RecyclerView, impl: Impl, itemTouchHelperCallback: ItemTouchHelper.Callback) : this(recyclerView, impl) {
        setUpTouchHelperCallback(itemTouchHelperCallback)
    }

    fun setUpTouchHelperCallback(itemTouchHelperCallback: ItemTouchHelper.Callback) {
        ItemTouchHelper(object : ItemTouchHelperCallbackWrapper(itemTouchHelperCallback) {
            override fun onSelectedChanged(viewHolder: RecyclerView.ViewHolder?, actionState: Int) {
                mIsItemTouchInEffect = actionState != 0
                super.onSelectedChanged(viewHolder, actionState)
            }
        }).attachToRecyclerView(mRecyclerView)
    }

    override fun getView(): View {
        return mRecyclerView
    }

    override fun isInAbsoluteStart(): Boolean {
        return !mIsItemTouchInEffect && mImpl.isInAbsoluteStart();
    }

    override fun isInAbsoluteEnd(): Boolean {
        return !mIsItemTouchInEffect && mImpl.isInAbsoluteEnd();
    }

    interface Impl {
        fun isInAbsoluteStart(): Boolean

        fun isInAbsoluteEnd(): Boolean
    }

    inner class ImplHorizLayout : Impl {
        override fun isInAbsoluteStart(): Boolean {
            return !mRecyclerView.canScrollHorizontally(-1)
        }

        override fun isInAbsoluteEnd(): Boolean {
            return !mRecyclerView.canScrollHorizontally(1)
        }

    }

    inner class ImplVerticalLayout : Impl {
        override fun isInAbsoluteStart(): Boolean {
            return !mRecyclerView.canScrollVertically(-1)
        }

        override fun isInAbsoluteEnd(): Boolean {
            return !mRecyclerView.canScrollVertically(1)
        }

    }

    open class ItemTouchHelperCallbackWrapper(val mCallback: ItemTouchHelper.Callback) : ItemTouchHelper.Callback() {
        override fun getMovementFlags(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder): Int {
            return mCallback.getMovementFlags(recyclerView, viewHolder)
        }

        override fun onMove(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder, target: RecyclerView.ViewHolder): Boolean {
            return mCallback.onMove(recyclerView, viewHolder, target)
        }

        override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
            mCallback.onSwiped(viewHolder, direction)
        }

        override fun convertToAbsoluteDirection(flags: Int, layoutDirection: Int): Int {
            return mCallback.convertToAbsoluteDirection(flags, layoutDirection)
        }

        override fun canDropOver(recyclerView: RecyclerView, current: RecyclerView.ViewHolder, target: RecyclerView.ViewHolder): Boolean {
            return mCallback.canDropOver(recyclerView, current, target)
        }

        override fun isLongPressDragEnabled(): Boolean {
            return mCallback.isLongPressDragEnabled()
        }

        override fun isItemViewSwipeEnabled(): Boolean {
            return mCallback.isItemViewSwipeEnabled()
        }

        override fun getBoundingBoxMargin(): Int {
            return mCallback.getBoundingBoxMargin()
        }

        override fun getMoveThreshold(viewHolder: RecyclerView.ViewHolder): Float {
            return mCallback.getMoveThreshold(viewHolder)
        }

        override fun chooseDropTarget(selected: RecyclerView.ViewHolder, dropTargets: MutableList<RecyclerView.ViewHolder>, curX: Int, curY: Int): RecyclerView.ViewHolder {
            return mCallback.chooseDropTarget(selected, dropTargets, curX, curY)
        }

        override fun onSelectedChanged(viewHolder: RecyclerView.ViewHolder?, actionState: Int) {
            mCallback.onSelectedChanged(viewHolder, actionState)
        }

        override fun onMoved(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder, fromPos: Int, target: RecyclerView.ViewHolder, toPos: Int, x: Int, y: Int) {
            mCallback.onMoved(recyclerView, viewHolder, fromPos, target, toPos, x, y)
        }

        override fun clearView(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder) {
            mCallback.clearView(recyclerView, viewHolder)
        }

        override fun onChildDraw(c: Canvas, recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder, dX: Float, dY: Float, actionState: Int, isCurrentlyActive: Boolean) {
            mCallback.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive)
        }

        override fun onChildDrawOver(c: Canvas, recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder?, dX: Float, dY: Float, actionState: Int, isCurrentlyActive: Boolean) {
            mCallback.onChildDrawOver(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive)
        }

        override fun getAnimationDuration(recyclerView: RecyclerView, animationType: Int, animateDx: Float, animateDy: Float): Long {
            return mCallback.getAnimationDuration(recyclerView, animationType, animateDx, animateDy)
        }

        override fun interpolateOutOfBoundsScroll(recyclerView: RecyclerView, viewSize: Int, viewSizeOutOfBounds: Int, totalSize: Int, msSinceStartScroll: Long): Int {
            return mCallback.interpolateOutOfBoundsScroll(recyclerView, viewSize, viewSizeOutOfBounds, totalSize, msSinceStartScroll)
        }
    }

}