package com.example.zhangshuai.kotlin

object CalUtils {
    /**
     * 遍历集合
     */
    fun traversingList(list: MutableList<String>) {
        for (str in list) {
            println(str)
        }
    }

    /**
     * @strs
     * 生成集合
     */
    fun createList(vararg strs: String) {
        val list = ArrayList<String>()
        for (str in strs) {
            list.add(str)
        }
    }
}