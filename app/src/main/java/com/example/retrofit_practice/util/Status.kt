package com.example.retrofit_practice.util

enum class Status(val value: String) {
    Confirmed("confirmed") {
        override fun toString(): String {
            return value
        }
    },
    Deaths("deaths"){
        override fun toString(): String {
            return value
        }
    }
//    Recovered ("recovered")
}