package com.example.zhangshuai.activity

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.commonui.widget.EventListenerView
import com.example.zhangshuai.gitlearingdemo.R
import kotlinx.android.synthetic.main.activity_event_listener_view.*

class EventViewActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_event_listener_view)
        view_event.eventListener = object : EventListenerView.EventListener {
            override fun onDown() {
                Log.e("zs", "onDown")
            }

            override fun onMove() {
//                Log.e("zs", "onMove")
            }

            override fun leftSlide() {
                Log.e("zs", "leftSlide")
            }

            override fun rightSlide() {
                Log.e("zs", "rightSlide")
            }

            override fun topSlide() {
                Log.e("zs", "topSlide")
            }

            override fun bottomSlide() {
                Log.e("zs", "bottomSlide")
            }

            override fun onClick() {
                Log.e("zs", "onClick")
            }

            override fun onUp() {
                Log.e("zs", "onUp")
            }

        }
    }
}