package com.example.zhangshuai.kotlin

class InitTest02{
    private var p1:String = ""
    private var p2:Int = 0
    constructor(){
        println("无参构造函数")
        this.p1 = "p1"
        this.p2 = 99
    }
    constructor(p1: String,p2:Int){
        println("次构造函数1")
        this.p1 = p1
        this.p2 = p2
        println("次构造函数2")
    }
    init {
        println("init方法")
        println("参数1${this.p1},参数2${this.p2}")
    }
}