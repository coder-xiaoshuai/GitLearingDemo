package com.example.zhangshuai.activity

import android.app.Activity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.zhangshuai.gitlearingdemo.R
import kotlinx.android.synthetic.main.activity_context_test.*

class ContextTestActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_context_test)
        btn_get_context.setOnClickListener {
            var isActivity = btn_get_context.context is Activity
            Toast.makeText(this@ContextTestActivity, "getContext${if (isActivity) "是" else "不是"}Activity", Toast.LENGTH_SHORT).show()
        }
    }
}