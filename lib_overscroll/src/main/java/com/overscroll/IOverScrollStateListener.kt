package com.overscroll

interface IOverScrollStateListener {
    fun onOverScrollStateChange(decor: IOverScrollDecor, oldState: Int, newState: Int)
}