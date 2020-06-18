package com.example.zhangshuai.mvp

import android.app.Activity
import android.content.Context
import android.util.Log

class TestPresenter {
    private var context: Context? = null
    private var mView: TestView? = null

    constructor(context: Context) {
        this.context = context
    }

    fun onbind(testView: TestView) {
        this.mView = testView
    }

    fun onDestory() {
        if (mView != null) {
            mView = null
        }

        if (context != null && context is Activity) {
            (context as Activity).finish()
        }

        Thread(Runnable {
            Thread.sleep(5000)
            Log.i("zs", "context= $context")
            context!!.applicationContext
                    .getSystemService(Context.WIFI_SERVICE)
        }).start()
    }
}