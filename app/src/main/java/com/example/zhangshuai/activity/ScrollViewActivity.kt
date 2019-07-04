package com.example.zhangshuai.activity

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.zhangshuai.gitlearingdemo.R
import kotlinx.android.synthetic.main.activity_scorll_view.*

class ScrollViewActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_scorll_view)

        scrollView.post {
            with(scrollView) {
                Log.e(TAG, "获取width$width")
                Log.e(TAG, "获取measureWidth$measuredWidth")
            }
        }

        linearlayout.post {
            with(linearlayout) {
                Log.e(TAG, "获取linear width$width")
                Log.e(TAG, "获取linear measureWidth$measuredWidth")
            }
        }

    }

    companion object {
        private const val TAG = "ScrollViewActivity"
    }
}