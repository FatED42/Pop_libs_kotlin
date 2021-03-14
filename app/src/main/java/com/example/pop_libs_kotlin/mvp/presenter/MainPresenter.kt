package com.example.pop_libs_kotlin.mvp.presenter

import com.example.pop_libs_kotlin.mvp.model.MainModel
import com.example.pop_libs_kotlin.mvp.view.MainView

class MainPresenter(private val view: MainView) {

    private val model = MainModel()

    fun counterClickOne() {
        val nextValue = model.next(0)
        view.setFirstButtonText(nextValue.toString())
    }

    fun counterClickTwo() {
        val nextValue = model.next(1)
        view.setSecondButtonText(nextValue.toString())
    }

    fun counterClickThree() {
        val nextValue = model.next(2)
        view.setThirdButtonText(nextValue.toString())
    }

}