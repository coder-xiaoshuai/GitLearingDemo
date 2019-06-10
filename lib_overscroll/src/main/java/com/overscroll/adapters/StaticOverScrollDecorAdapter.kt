package com.overscroll.adapters

import android.view.View

class StaticOverScrollDecorAdapter(val mView: View) : IOverScrollDecoratorAdapter {
    override fun getView(): View {
        return mView
    }

    override fun isInAbsoluteStart(): Boolean {
        return true
    }

    override fun isInAbsoluteEnd(): Boolean {
        return true
    }

}