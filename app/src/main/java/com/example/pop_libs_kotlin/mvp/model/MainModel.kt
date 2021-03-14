package com.example.pop_libs_kotlin.mvp.model

class MainModel {

    private val counters = mutableListOf(0, 0, 0)

    private fun getCurrent(index: Int) = counters[index]

    fun next(index: Int): Int {
        counters[index]++
        return getCurrent(index)
    }

}