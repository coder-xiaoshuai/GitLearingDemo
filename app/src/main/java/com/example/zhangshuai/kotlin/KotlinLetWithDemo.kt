package com.example.zhangshuai.kotlin

class KotlinLetWithDemo {

    /**
     * let扩展函数的实际上是一个作用域函数，当你需要去定义一个变量在一个特定的作用域范围内，
     * let函数的是一个不错的选择；let函数另一个作用就是可以避免写一些判断null的操作。
     */
    private fun letTest() {
        val string = "学习kotlin的第一天"
        string?.let {
            it.trim()
            it.replace("一", "N")
        }
    }


    /**
     * with函数和前面的几个函数使用方式略有不同，因为它不是以扩展的形式存在的。它是将某对象作为函数的参数，
     * 在函数块内可以通过 this 指代该对象。返回值为函数块的最后一行或指定return表达式。
     */
    private fun withTest() {
        val string = "哈哈哈哈哈哈"
        with(string) {
            trim()
            get(0)
        }
    }


    /**
     * run函数实际上可以说是let和with两个函数的结合体，run函数只接收一个lambda函数为参数，
     * 以闭包形式返回，返回值为最后一行的值或者指定的return的表达式。
     */
    private fun runTest() {
        val string = "我我我我我我我"
        string?.run {

        }
    }

    /**
     * 从结构上来看apply函数和run函数很像，唯一不同点就是它们各自返回的值不一样，
     * run函数是以闭包形式返回最后一行代码的值，而apply函数的返回的是传入对象的本身。
     */
    private fun applyTest() {
        val string="啦啦啦啦啦啦啦"
        string?.apply {

        }
    }

    /**
     * also函数的结构实际上和let很像唯一的区别就是返回值的不一样，let是以闭包的形式返回，
     * 返回函数体内最后一行的值，如果最后一行为空就返回一个Unit类型的默认值。
     * 而also函数返回的则是传入对象的本身
     */
    private fun alsoTest(){
        val string = "略略略略略略"
        string?.also {

        }
    }
}