package com.example.zhangshuai.kotlin

import java.util.*

class HelloWorld {
    companion object {
        fun max(m: Int, n: Int): Int {
            return m + n
        }

        fun initPerson(name: String, age: Int, sex: Boolean) {
            var person = Person(name, age, sex)
            print("创建了一个Person${if (sex) "他" else "她"}的名字：$name 他的年纪$age")
        }

    }


    fun getShape(shape: SHAPE): String {
        return when (shape) {
            SHAPE.TRIANGLE -> {
                "三角形"
            }
            SHAPE.RECT -> {
                "长方形"
            }
            SHAPE.CIRCLE -> {
                "圆形"
            }
            else -> {
                "其他形状"
            }
        }
    }

    class Person(name: String, age: Int, sex: Boolean)

    enum class SHAPE(perimeter: Int) {
        TRIANGLE(100), RECT(120), CIRCLE(130), NONE(-1)
    }

    class Circle {
        var radius = 0
    }

    /**
     * 枚举测试
     */
    enum class Color(val r: Int = 0, val g: Int = 0, val b: Int = 0) {
        RED(255, 0, 0), ORANGE, YELLOW, GREEN, BLUE, INDIGO, VIOLEF
    }

    /**
     * 根据分数获取成绩等级
     */
    fun getScoreLevel(score: Int): String {
        when (score) {
            in 90..100 -> {
                return "A"
            }
            in 80..90 -> {
                return "B"
            }
            in 70..80 -> {
                return "C"
            }
            in 60..70 -> {
                return "D"
            }
            else -> {
                return "不及格"
            }
        }
    }

    /**
     * when不带参数用法
     */
    fun getSumBigerThanTarget(number1: Int, number2: Int, target: Int): Boolean {
        when {
            (number1 + number2 > target) -> {
                return true;
            }
            else -> {
                return false;
            }
        }
    }


    fun whileTest() {
        //while的两种方式
        while (true) {
        }

        do {
        } while (true)

        //for循环
        val oneToTen = 0..10
        val index = 0
        for (index in oneToTen step 2) {
        }
    }

    /**
     * 遍历集合
     */
    fun iteratorMap() {
        val map = TreeMap<Char, String>()
        for (c in 'A'..'F') {
            val value = Integer.toBinaryString(c.toInt())
            map[c] = value
        }

        for ((key, value) in map) {
            println("key $key,value $value")
        }
    }

    fun iteraorList() {
        val list = arrayListOf<String>("10", "11", "12")
        for ((index, element) in list.withIndex()) {
            println("index $index:element:$element")
        }
    }

}