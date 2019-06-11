package com.example.zhangshuai.kotlin

import java.io.BufferedReader
import java.util.*
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

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

    fun tryTest(number: Int) {
        val percentage = if (number in 0..100) number else throw IllegalArgumentException("A percentage value must be between 0 and 100:$number")
    }

    fun readerNumber(reader: BufferedReader): Int? {
        return try {
            val line = reader.readLine()
            Integer.parseInt(line)
        } catch (e: Exception) {
            null
        } finally {
            reader.close()
        }
    }

    fun collectionTest() {
        val set = hashSetOf<Int>(0, 1, 2)
        val list = arrayListOf<Int>(0, 1, 2)
        val map = hashMapOf<Int, String>(0 to "zero", 1 to "one", 2 to "two")
    }

    /**
     * 可变参数函数
     */
    fun method(vararg values: Int) {
        var string = "hello world!!!"
        string.let { }

        var int1 = 111
    }

    /**
     * 键值对的处理 ： 中缀调用和解构生命
     */
    fun mapTest() {
        val map = mapOf(1 to "one", 2 to "two", 3 to "three")
        for ((key, value) in map.entries) {
            println("key$key,value$value")
        }
        //解构声明
        val (key, value) = 1 to "one"
    }

    class User(val id: Int, val name: String, val address: String)


    //实际开发中可以把局部函数放到User的扩展函数中
    fun saveUser(user: User) {
        //局部函数
        fun validate(value: String, filedName: String) {
            if (value.isEmpty()) {
                throw IllegalArgumentException("Can't save user ${user.id}: empty$filedName")
            }
        }

        validate(user.name, "name")
        validate(user.address, "address")
    }


    /**
     * 单例模式1
     */
    class Singlon(params1: String? = null, params2: String? = null) {
        companion object {
            var instance: Singlon? = null
                get() {
                    if (field == null) {
                        synchronized(Singlon::class.java) {
                            if (field == null) {
                                field = Singlon("参数1", "参数2")
                            }
                        }
                    }
                    return field
                }
        }
    }


    fun calOptionTest() {
        var num = 0
        num += 21
        num %= 5
        println("num == $num")


        var a = 3
        var b = 5

    }

    interface Base {
        fun success()
    }

    class Derived(b: Base) : Base by b


    class NotNullVar<T : Any> : ReadWriteProperty<Any?, T> {
        var value: T? = null
        override fun getValue(thisRef: Any?, property: KProperty<*>): T {
            return value
                    ?: throw IllegalArgumentException("Property ${property.name} should be initialized before get.")
        }

        override fun setValue(thisRef: Any?, property: KProperty<*>, value: T) {
            this.value = value
        }

    }

    abstract class ObservableProperty<T>(initialValue: T) : ReadWriteProperty<Any?, T> {
        private var value = initialValue

        protected open fun beforeChange(property: KProperty<*>, oldValue: T, newValue: T): Boolean = true


        protected open fun afterChange(property: KProperty<*>, oldValue: T, newValue: T): Unit {}

        public override fun getValue(thisRef: Any?, property: KProperty<*>): T {
            return value
        }

        public override fun setValue(thisRef: Any?, property: KProperty<*>, value: T) {
            val oldValue = this.value //修改前的值

            //beforeChange一直返回true 不知此段代码意义何在
            if (!beforeChange(property, oldValue, value)) {
                return
            }
            this.value = value //修改后的值
            //调用上一段代码中重写的方法
            afterChange(property, oldValue, value)
        }
    }

    inline fun <T> observable(initialValue: T, crossinline onChange: (property: KProperty<*>, oldValue: T, newValue: T) -> Unit):
            ReadWriteProperty<Any?, T> = object : ObservableProperty<T>(initialValue) {
        override fun afterChange(property: KProperty<*>, oldValue: T, newValue: T) = onChange(property, oldValue, newValue)
    }


    var int2: Int by observable(1) { property, oldValue, newValue ->
        //oldvalue是修改前的值,newValue是修改后的值
    }

    val lazyValue: String by lazy {
        println("havana")
        ""
    }

    class UserByMap(val map: Map<String, Any?>) {
        val name: String by map
        val age: Int by map
    }

    val userByMap = UserByMap(mapOf("name" to "John Doe", "age" to 30))


    //高阶函数测试
//    fun <T, R> Collection<T>.fold(initial: R, combine: (acc: R, nextElement: T) -> R): R {
//        var accumulator: R = initial
//        for (element: T in this) {
//            accumulator = combine(accumulator, element)
//        }
//        return accumulator
//    }


    fun <T, R> Collection<T>.fold(
            initial: R,
            combine: (acc: R, nextElement: T) -> R
    ): R {
        var accumulator: R = initial
        for (element: T in this) {
            accumulator = combine(accumulator, element)
        }
        return accumulator
    }

    fun foldTest() {
        val items1 = listOf(1, 2, 3, 4, 5)

// Lambdas 表达式是花括号括起来的代码块。
        items1.fold(0
        ) {
            // 如果一个 lambda 表达式有参数，前面是参数，后跟“->”
            acc: Int, i: Int ->
            print("acc = $acc, i = $i, ")
            val result = acc + i
            println("result = $result")
            // lambda 表达式中的最后一个表达式是返回值：
            result
        }
        plusXY(1, 2)
    }

    var plusXY = fun(x: Int, y: Int): Int = x.plus(y)


    //默认参数测试
    fun methodThreeParams(p1: Int = 1, p2: String = "", p3: Boolean = false) {

    }

    fun methodThreeParamsCall(){
        methodThreeParams(1)
    }

}