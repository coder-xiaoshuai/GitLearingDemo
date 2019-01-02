package com.example.zhangshuai.kotlin

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.zhangshuai.gitlearingdemo.R
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        text.setOnClickListener {
            Toast.makeText(this@MainActivity, "提示信息", Toast.LENGTH_SHORT).show()
        }
    }

    fun getCalResult(number1: Int, number2: Int): Int {
        return number1 + number2
    }

    override fun onRestart() {
        super.onRestart()
    }
}
