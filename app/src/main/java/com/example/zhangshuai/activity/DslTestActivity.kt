package com.example.zhangshuai.activity

import android.os.Bundle
import android.view.Gravity
import androidx.appcompat.app.AppCompatActivity
import org.jetbrains.anko.*

class DslTestActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        verticalLayout {
            button {
                text = "我是一个button"
                textSize = 30f
                allCaps = false
            }

            textView {
                text = "我是一个textView"
                height = 200
                gravity = Gravity.CENTER
            }

            editText {
                hint = "请输入内容"
            }

        }
    }

}