package com.example.zhangshuai.kotlin

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.zhangshuai.activity.*
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

        btn_auto_text.setOnClickListener {
            var intent = Intent(this@MainActivity, AutoTextActivity::class.java)
            startActivity(intent)
        }
        btn_clickable.setOnClickListener {
            var intent = Intent(this@MainActivity, ClickableSpanActivity::class.java)
            startActivity(intent)
        }

        btn_path_demo.setOnClickListener {
            var intent = Intent(this@MainActivity, PathDemoActivity::class.java)
            startActivity(intent)
        }

        btn_rv.setOnClickListener {
            var intent = Intent(this@MainActivity, RvActivity::class.java)
            startActivity(intent)
        }

        btn_praise.setOnClickListener {
            var intent = Intent(this@MainActivity, PraiseDemoActivity::class.java)
            startActivity(intent)
        }

        btn_nine_grid.setOnClickListener {
            var intent = Intent(this@MainActivity, NineGridLayoutActivity::class.java)
            startActivity(intent)
        }

        btn_gif.setOnClickListener {
            var intent = Intent(this@MainActivity, GifActivity::class.java)
            startActivity(intent)
        }

        btn_event.setOnClickListener {
            var intent = Intent(this@MainActivity, EventTestActivity::class.java)
            startActivity(intent)
        }

        btn_my_butter_knife.setOnClickListener {
            var intent = Intent(this@MainActivity, MyButterKnifeActivity::class.java)
            startActivity(intent)
        }

    }

    fun getCalResult(number1: Int, number2: Int): Int {
        return number1 + number2
    }

    override fun onRestart() {
        super.onRestart()
    }

    override fun onPause() {
        super.onPause()
        Log.e("zs", "isFinishing$isFinishing")
    }
}
