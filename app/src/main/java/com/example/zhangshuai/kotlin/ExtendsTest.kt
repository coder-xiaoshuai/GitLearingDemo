package com.example.zhangshuai.kotlin

open class Car {

    protected var color = "red"


}

class Bus : Car() {
    fun changeColor(targetColor: String) {
        this.color = targetColor
    }
}

fun carTypeCheck(car: Car) {
    when (car) {
        is Car -> {

        }

        is Bus -> {

        }

        else -> {

        }
    }
}

sealed class Book {
    class EnglishBook(val p1: Int) : Book()
    class ChineseBook(var p2: String) : Book()
}


class InitTest(var text1: String) {
    var string1 = text1

    lateinit var string2: String

    init {
        print("我是init方法")

        print("string1$string1")

//        print("string2$string2")
        string2 = "22222222"
        println("string2$string2")

    }

}


open class GameRole(val nickName: String, val level: Int)

class DnfGameRole(nickName: String, val userId: Long, val rank: Int) : GameRole("还没有使用昵称", 9999)

class LengthCounter {
    var counter: Int = 0
        private set

    fun addWord(word: String) {
        counter += word.length
    }
}


class Client(val name: String, val postalCode: Int) {
    fun copy(name: String = "defaultName", postalCode: Int = 0): Client {
        return Client(name, postalCode)
    }
}

//代理
class DelegatingCollection<T>(private val innerList: Collection<T> = ArrayList<T>()) : Collection<T> by innerList {}


//单例模式
object SingleClass {

}


class P1 {
    //嵌套类单例  在别的类容器内调用也是同一个对象
    object PTool {
        fun cal(p1: P1, p2: P1) {

        }
    }
}

//半生对象
class A {
    companion object {
        fun method() {
            print("我是伴生对象的方法 调用只需要A.method()")
        }
    }

}


class MyClassMate(val birthDay: Int) {
}

fun getMaxFromList(list: ArrayList<MyClassMate>) {
    list.maxBy { it.birthDay }
}

interface Callback<T> {
    fun call(t: T)
}

class L(var callback: Callback<Boolean>) {
}

fun setCallback(){
    var l = L(object : Callback<Boolean> {
        override fun call(t: Boolean) {

        }
    })
}


