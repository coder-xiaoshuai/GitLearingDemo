package com.example.zhangshuai.kotlintest

import org.junit.Test

import org.junit.Assert.*

class Test02Test {

    @Test
    fun testSub() {
        val test02 = Test02();
        assertEquals(1, test02.testSub(4, 3))
    }
}