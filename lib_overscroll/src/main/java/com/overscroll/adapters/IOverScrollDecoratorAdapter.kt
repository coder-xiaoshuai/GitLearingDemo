package com.overscroll.adapters

import android.view.View

interface IOverScrollDecoratorAdapter {

    fun getView():View
    fun isInAbsoluteStart(): Boolean
    fun isInAbsoluteEnd(): Boolean
}