package com.overscroll.adapters

import android.view.View
import android.widget.ScrollView

class ScrollViewOverScrollDecorAdapter(val mView: ScrollView) : IOverScrollDecoratorAdapter {

    override fun getView(): View {
        return mView
    }

    override fun isInAbsoluteStart(): Boolean {
        return !mView.canScrollVertically(-1)
    }

    override fun isInAbsoluteEnd(): Boolean {
        return !mView.canScrollVertically(1)
    }
}