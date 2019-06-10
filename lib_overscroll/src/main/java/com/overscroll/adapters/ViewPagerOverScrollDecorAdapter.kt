package com.overscroll.adapters

import android.view.View
import androidx.viewpager.widget.ViewPager

class ViewPagerOverScrollDecorAdapter(val mViewPager: ViewPager) : IOverScrollDecoratorAdapter, ViewPager.OnPageChangeListener {

    var mLastPagerPosition = 0
    var mLastPagerScrollOffset = 0f

    init {
        mViewPager.addOnPageChangeListener(this)
        mLastPagerPosition = mViewPager.currentItem
        mLastPagerScrollOffset = 0f
    }

    override fun getView(): View {
        return mViewPager
    }

    override fun isInAbsoluteStart(): Boolean {
        return mLastPagerPosition == 0 && mLastPagerScrollOffset == 0f;
    }

    override fun isInAbsoluteEnd(): Boolean {
        return mLastPagerPosition == mViewPager.getAdapter()?.getCount()?.minus(1) &&
                mLastPagerScrollOffset == 0f
    }

    override fun onPageScrollStateChanged(state: Int) {
    }

    override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
        mLastPagerPosition = position
        mLastPagerScrollOffset = positionOffset
    }

    override fun onPageSelected(position: Int) {
    }
}