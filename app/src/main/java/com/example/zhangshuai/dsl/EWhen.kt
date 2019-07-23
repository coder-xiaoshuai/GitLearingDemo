package com.example.zhangshuai.dsl


fun <T : Comparable<T>> eWhen(target: T, tester: Tester<T>.() -> Unit) {
    val test = Tester(target)
    test.tester()
    test.funFiltered?.invoke() ?: return
}

class Tester<T : Comparable<T>>(val original: T) {
    var funFiltered: (() -> Unit)? = null


    infix fun Boolean.then(block: () -> Unit) {
        if (this && funFiltered == null) {
            funFiltered = block
        }
    }

    infix fun Boolean.after(block: () -> Unit) {
        if (this && funFiltered == null) {
            funFiltered = block
        }
    }

    fun lt(arg: T) = original < arg
    fun gt(arg: T) = original > arg
    fun ge(arg: T) = original >= arg
    fun le(arg: T) = original <= arg
    fun eq(arg: T) = original == arg
    fun ne(arg: T) = original != arg
    fun ct(arg: Collection<T>) = original in arg
    fun ct(arg: String) = original as String in arg
    fun nc(arg: Collection<T>) = original !in arg
    fun nc(arg: String) = original as String !in arg
}

fun eWhenTest() {
    eWhen(30) {

        (original is Int) then {
            print("这是一个整数")
        }

        (lt(100)) then {

        }

    }
}