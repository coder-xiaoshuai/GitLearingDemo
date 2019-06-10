package com.overscroll

interface OnOverScrollListener {

    fun onTopOverScroll()

    fun onBottomOverScroll()

    fun onLeftOverScroll()

    fun onRightOverScroll()

    fun onFlingOverScroll()

    fun canFlingOverScroll(): Boolean
}