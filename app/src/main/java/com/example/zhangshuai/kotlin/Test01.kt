package com.example.zhangshuai.kotlin

class Test01 {
    var text: String = "lskjflksd"
    var text2: String? = "slkdjflasj"

    fun method() {
        text.substring(0)
        text2?.substring(2)
    }


    fun traversingList() {
        var list = ArrayList<String>(100)

        //遍历方式一
        for (string in list) {
            println(string)
        }

        //遍历方式二
        for ((index, string) in list.withIndex()) {

        }

        //遍历方式三
        list.forEach() {}

        list.forEachIndexed { index, s ->
            println("我是第${index.plus(1)}个元素:$s")
        }

        for (i in 100 downTo 0 step 5) {

        }
    }

    //饿汉式单例
    object Singleton1 {}

    //懒汉式单例
    class Singleton2 private constructor() {
        companion object {
            private var instance: Singleton2? = null
                get() {
                    if (field == null) {
                        synchronized(Singleton2::class.java) {
                            if (field == null) {
                                field = Singleton2()
                            }
                        }
                    }
                    return field
                }
        }
    }

    //常用扩展函数测试
    fun expandMethodTest() {
        //run,with,T.run,T.let,T.also,和T.apply
        var animal = "cat"
        run {
            val animal = "dog"
            println(animal)   // dog
        }
        println(animal)       //cat


        var string1 = "hksjflask"
        //可以判空
        string1?.run {
            //可以直接调用String的方法 不用显示写string1去掉用
        }

        //不可以提前判空
        with(string1) {
            string1?.substring(1)
        }

        string1.let {
            StringWrapper(it)
        }.also { it.getSize() }
    }


    class StringWrapper(var string: String){
        fun getSize():Int{
            return string.length
        }
    }

    class Person111(var name:String,var age:Int){

        fun printInfo() {
            println("当前name${this.name}和年龄${this.age}")
        }
    }
}