package com.example.zhangshuai.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.zhangshuai.interface_k.KotlinCallback

class TestActivity01 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        method("", object : KotlinCallback {
            override fun callback(str: String) {}
        })
    }

    private fun method(aa: String, callback: KotlinCallback) {}
}