package com.example.zhangshuai.kotlintest

import org.junit.runner.RunWith
import org.junit.runners.Suite

@RunWith(Suite::class)
@Suite.SuiteClasses(Test01Test::class, Test02Test::class)
class SuiteTest {
}