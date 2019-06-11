package com.example.zhangshuai.kotlin

import kotlinx.coroutines.*

object KotlinMain{
    fun GlobalScopeTest() {
        GlobalScope.launch {
            delay(5000)
            print("World!!")
        }


    }

    fun GlobalScopeTest2(){
       runBlocking {
           delay(5000)
           print("World!!")
       }
        println("Hello,")
    }

    fun GlobalScopeTest3(){
        val deferred=(1..1_000_000).map {
            n ->
            GlobalScope.async {
                workload(n)
            }
        }

        runBlocking {
            val sum = deferred.sumBy { it.await() }
            println("Sum: $sum")
        }
    }

    suspend fun workload(n: Int): Int {
        delay(1000)
        return n
    }
}