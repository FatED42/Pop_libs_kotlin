package com.example.pop_libs_kotlin.mvp.presenter

import com.example.pop_libs_kotlin.mvp.model.navigation.IScreens
import com.example.pop_libs_kotlin.mvp.view.MainView
import com.github.terrakok.cicerone.Router
import moxy.MvpPresenter

class MainPresenter(private val router: Router, private val screens: IScreens) : MvpPresenter<MainView>() {

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        router.replaceScreen(screens.users())
    }

    fun backClicked() {
        router.exit()
    }

}