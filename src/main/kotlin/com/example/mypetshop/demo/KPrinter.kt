package com.example.mypetshop.demo

class KPrinter {
    private val helper: JPrinter = JPrinter()

    fun print(language: String) {
        println("${this::class.simpleName} is printing from $language")
    }

    fun printWithHelper() {
        helper.print("Kotlin")
    }
}