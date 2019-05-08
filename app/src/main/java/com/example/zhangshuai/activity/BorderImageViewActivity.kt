package com.example.zhangshuai.activity

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.zhangshuai.gitlearingdemo.R
import kotlinx.android.synthetic.main.circle_image_border.*

class BorderImageViewActivity :AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.circle_image_border)

        image_border.setOnClickListener {
            Toast.makeText(this@BorderImageViewActivity, "点击了头像", Toast.LENGTH_SHORT).show()
        }
    }
}