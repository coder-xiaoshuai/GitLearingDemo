package com.example.zhangshuai.activity

import android.os.Bundle
import android.widget.Toast
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
                Toast.makeText(this@EventViewActivity, "onDown", Toast.LENGTH_SHORT).show()
            }

            override fun onMove() {
                Toast.makeText(this@EventViewActivity, "onMove", Toast.LENGTH_SHORT).show()
            }

            override fun leftSlide() {
                Toast.makeText(this@EventViewActivity, "leftSlide", Toast.LENGTH_SHORT).show()
            }

            override fun rightSlide() {
                Toast.makeText(this@EventViewActivity, "rightSlide", Toast.LENGTH_SHORT).show()
            }

            override fun topSlide() {
                Toast.makeText(this@EventViewActivity, "topSlide", Toast.LENGTH_SHORT).show()
            }

            override fun bottomSlide() {
                Toast.makeText(this@EventViewActivity, "bottomSlide", Toast.LENGTH_SHORT).show()
            }

            override fun onClick() {
                Toast.makeText(this@EventViewActivity, "onClick", Toast.LENGTH_SHORT).show()
            }

            override fun onUp() {
                Toast.makeText(this@EventViewActivity, "onUp", Toast.LENGTH_SHORT).show()
            }

        }
    }
}