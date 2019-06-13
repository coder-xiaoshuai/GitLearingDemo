package com.example.zhangshuai.kotlin

import java.util.*

class Test02 {

    fun <T : Int> getX(t: T) {

    }

    internal open inner class Creature {
        open val name: String
            get() = "Creature"
    }

    internal open inner class Animal : Creature() {

        override val name: String
            get() = "Animal"
    }

    internal inner class Dog : Animal() {
        override val name: String
            get() = "Dog"

        fun eat() {

        }
    }

    internal inner class Cat : Animal() {
        override val name: String
            get() = "Cat"

        fun run() {}
    }

    fun listTest() {
        val list = ArrayList<Creature>()
        list.add(Dog())
        list.add(Cat())

        val list2 = ArrayList<Dog>()

    }
}
