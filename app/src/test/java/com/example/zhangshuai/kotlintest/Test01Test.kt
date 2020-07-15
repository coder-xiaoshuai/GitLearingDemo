package com.example.zhangshuai.kotlintest

import org.junit.Test

import org.junit.Assert.*

class Test01Test {

    @Test
    fun testAdd() {
        val test01 = Test01();
        assertEquals(2, test01.testAdd(1, 1))
    }
}