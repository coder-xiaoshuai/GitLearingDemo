package com.example.zhangshuai.kotlin

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


fun GlobalScopeTest() {
    GlobalScope.launch {
        delay(5000)
        print("World!!")
    }

    println("Hello,")
    Thread.sleep(2000L)
}