package com.example.zhangshuai.test

object ZhengZeTest {
    @JvmStatic
    fun main(args: Array<String>) {
//        matchStr()
        val firstPart = "/comment/"
        val secondPart = "/detail"
        println(matchStr("sfasfsafsaf/comment/12131/detailsfsafsafasasf ",
                ".*$firstPart\\w*$secondPart.*"))
    }

    fun test(){
        val firstPart = "/comment/"
        val secondPart = "/detail"
        println(matchStr("sfasfsafsaf/comment/12131/detailsfsafsafasasf ",
                ".*$firstPart\\w*$secondPart.*"))
    }

    fun matchStr(str: String, reg: String?): Boolean {
        return str.matches(reg!!.toRegex())
    }
}