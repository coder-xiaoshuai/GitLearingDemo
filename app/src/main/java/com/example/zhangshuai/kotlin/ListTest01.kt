package com.example.zhangshuai.kotlin

class ListTest01 {
    var listTest01: MutableList<String>? = null
    fun testList() {
        println("为null返回什么${
        listTest01?.isNullOrEmpty()
        }")
        if (listTest01?.isNullOrEmpty() == true) {
        }
    }
}