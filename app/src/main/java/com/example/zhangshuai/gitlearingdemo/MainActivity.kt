package com.example.zhangshuai.gitlearingdemo

import android.support.v7.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun getCalResult(number1: Int, number2: Int): Int {
        return number1 + number2
    }
}
