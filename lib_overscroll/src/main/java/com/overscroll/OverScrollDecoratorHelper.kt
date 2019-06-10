package com.overscroll

import android.widget.ListView
import androidx.recyclerview.widget.RecyclerView

class OverScrollDecoratorHelper {

    companion object {
        const val ORIENTATION_VERTICAL = 0
        const val ORIENTATION_HORIZONTAL = 1
        fun setUpOverScroll(recyclerView: RecyclerView, orientation: Int) {
            when (orientation) {
                ORIENTATION_HORIZONTAL -> {
                    //todo
                }
                ORIENTATION_VERTICAL -> {
                    //todo
                }
                else -> {
                    throw IllegalArgumentException("orientation")
                }
            }
        }

        fun setUpOverScroll(listView: ListView):IOverScrollDecor{
            return null!!
        }
    }


}