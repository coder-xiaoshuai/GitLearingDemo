package com.example.zhangshuai.activity

import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import com.example.zhangshuai.gitlearingdemo.R
import kotlinx.android.synthetic.main.activity_request_layout.*

class RequestLayoutActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_request_layout)
        val handler = Handler();
        btn_container_invalidate.setOnClickListener{
            for (i in 0..10){
                Thread.sleep(50)
                container_view.invalidate()
            }

        }

        btn_container_request_layout.setOnClickListener{
            for (i in 0..10){
                Thread.sleep(50)
                container_view.requestLayout()
            }

        }

        btn_child_view_invalidate.setOnClickListener{
            for (i in 0..10){
                Thread.sleep(50)
                child_view.invalidate()
            }

        }

        btn_child_view_request_layout.setOnClickListener{
            for (i in 0..10){
                Thread.sleep(50)
                child_view.requestLayout()
            }

        }

    }
}