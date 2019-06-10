package com.overscroll.adapters

import android.view.View
import android.widget.AbsListView

class AbsListViewOverScrollDecorAdapter(val mView: AbsListView) : IOverScrollDecoratorAdapter {

    override fun getView(): View {
        return mView
    }

    override fun isInAbsoluteStart(): Boolean {
        return mView.childCount > 0 && !canScrollListUp()
    }

    override fun isInAbsoluteEnd(): Boolean {
        return mView.childCount > 0 && !canScrollListDown()
    }

    fun canScrollListUp(): Boolean {
        val firstTop = mView.getChildAt(0).top
        val firstPosition = mView.firstVisiblePosition
        return firstPosition > 0 || firstTop < mView.listPaddingTop
    }

    fun canScrollListDown(): Boolean {
        val childCount = mView.childCount
        val itemCount = mView.count
        val firstPosition = mView.firstVisiblePosition
        val lastPosition = firstPosition + childCount
        val lastBottom = mView.getChildAt(childCount - 1).bottom
        return lastPosition < itemCount || lastBottom > mView.height - mView.listPaddingBottom
    }
}