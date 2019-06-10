package com.overscroll

import android.view.View

interface IOverScrollDecor {
    fun getView(): View

    fun setOverScorllStateListener(listener: IOverScrollStateListener)

    fun setOverScorllUpdateListener(listener: IOverScrollUpdateListener)

    fun getCurrentState(): Int

    fun detach()

}