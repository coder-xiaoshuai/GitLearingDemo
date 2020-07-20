package com.example.zhangshuai.kotlin

class InitTest01(p1: String, p2: Int) {
    var p1: String = ""
    var p2: Int = 0

    init {
        println("参数1$p1,参数2$p2")
    }
}