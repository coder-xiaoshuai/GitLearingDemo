package com.example.zhangshuai.activity

import android.os.Bundle
import android.os.Handler
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.zhangshuai.gitlearingdemo.R
import kotlinx.android.synthetic.main.activity_custom_show.*

class CustomShowActivity : AppCompatActivity() {


    override fun onAttachedToWindow() {
//        val handler = Handler()
//        handler.post{
//            Log.e("zs", "onAttachedToWindow${imageView.width}")
//            Log.e("zs", "onAttachedToWindow${imageView.height}")
//        }
        super.onAttachedToWindow()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_custom_show)

//        val handler = Handler()
//        handler.post{
//            Log.e("zs", "handler获取width${imageView.width}")
//            Log.e("zs", "handler获取measureWidth${imageView.height}")
//        }
//
//        handler.post(object :Runnable{
//            override fun run() {
//                Log.e("zs", "Runnable获取width${imageView.width}")
//                Log.e("zs", "Runnable获取measureWidth${imageView.height}")
//            }
//        })

        Log.e("tag", "handler onCreate 获取width${imageView.width}")
        Log.e("tag", "handler onCreate 获取measureWidth${imageView.height}")

    }


    override fun onResume() {
        super.onResume()

        Log.e("tag", "handler onResume 获取width${imageView.width}")
        Log.e("tag", "handler onResume 获取measureWidth${imageView.height}")

        val handler = Handler()
        handler.post {
            Log.e("tag", "handler onResume 获取width${imageView.width}")
            Log.e("tag", "handler onResume 获取height${imageView.height}")
        }

        imageView.post {
            Log.e("tag", "view.post onResume 获取width${imageView.width}")
            Log.e("tag", "view.post onResume 获取height${imageView.height}")
        }
    }



}