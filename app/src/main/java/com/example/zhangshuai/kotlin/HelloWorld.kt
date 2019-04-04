package com.example.zhangshuai.kotlin

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


    fun getShape(shape:SHAPE):String {
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
        TRIANGLE(100), RECT(120), CIRCLE(130),NONE(-1)
    }

    class Circle{
        var radius = 0
    }

}