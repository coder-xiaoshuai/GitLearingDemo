package com.example.zhangshuai.kotlin

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.zhangshuai.activity.ImageVideoDemoActivity
import com.example.zhangshuai.activity.NineImageActivity
import com.example.zhangshuai.gitlearingdemo.R
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        text.setOnClickListener {
            Toast.makeText(this@MainActivity, "提示信息", Toast.LENGTH_SHORT).show()
        }

        btn_image_video_demo.setOnClickListener {
            var intent = Intent(this@MainActivity, ImageVideoDemoActivity::class.java)
            startActivity(intent)
        }
        btn_nine_image_preview.setOnClickListener {
            var intent = Intent(this@MainActivity, NineImageActivity::class.java)
            startActivity(intent)
        }
    }

    fun getCalResult(number1: Int, number2: Int): Int {
        return number1 + number2
    }

    override fun onRestart() {
        super.onRestart()
    }
}
