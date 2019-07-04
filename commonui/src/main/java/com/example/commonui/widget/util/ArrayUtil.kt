package com.example.commonui.widget.util


fun Array<kotlin.Float>.sumToIndex(index: Int): kotlin.Float {
    var sum = 0.0f
    for (i in 0..index) {
        sum += this[i]
    }
    return sum
}