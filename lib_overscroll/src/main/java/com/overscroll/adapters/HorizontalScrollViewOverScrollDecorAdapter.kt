package com.overscroll.adapters

import android.view.View
import android.widget.HorizontalScrollView

class HorizontalScrollViewOverScrollDecorAdapter(val mView: HorizontalScrollView) : IOverScrollDecoratorAdapter {


    override fun getView(): View {
        return mView
    }

    override fun isInAbsoluteStart(): Boolean {
        return !mView.canScrollHorizontally(-1)
    }

    override fun isInAbsoluteEnd(): Boolean {
        return !mView.canScrollHorizontally(1)
    }
}