package com.example.zhangshuai.kotlin

typealias STRING = ArrayList<String>

class LambdaLearning {

    fun <T, R> Collection<T>.fold(initial: R, combine: (acc: R, nextElement: T) -> R): R {

        var accumulator: R = initial
        for (element: T in this) {
            accumulator = combine(accumulator, element)
        }

        return accumulator
    }


    fun method(ty: STRING) {

    }

    companion object {

    }


}