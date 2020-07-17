package com.example.zhangshuai.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.common.ToastUtils
import com.example.zhangshuai.gitlearingdemo.R
import kotlinx.android.synthetic.main.activity_tinker_test.*

class TinkerTestActivity :AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tinker_test)
        btn_toast.setOnClickListener {
            ToastUtils.show("正确！！！！")
        }
    }
}