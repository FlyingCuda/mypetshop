package com.example.mypetshop.demo

class KPrinter {
    private val helper: JPrinter = JPrinter()

    companion object {
        private val printer = KPrinter()
        @JvmStatic
        fun printFromKotlin() {
            printer.print("Kotlin")
        }
    }

    fun print(language: String) {
        println("${this::class.simpleName} is printing from $language")
    }

    fun printWithHelper() {
        helper.print("Kotlin")
    }
}