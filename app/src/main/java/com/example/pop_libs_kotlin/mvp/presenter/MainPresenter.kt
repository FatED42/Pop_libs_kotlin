package com.example.pop_libs_kotlin.mvp.presenter

import com.example.pop_libs_kotlin.mvp.model.navigation.IScreens
import com.example.pop_libs_kotlin.mvp.view.MainView
import com.github.terrakok.cicerone.Router
import moxy.MvpPresenter
import javax.inject.Inject

class MainPresenter : MvpPresenter<MainView>() {

    @Inject lateinit var router: Router
    @Inject lateinit var screens: IScreens

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        router.replaceScreen(screens.users())
    }

    fun backClicked() {
        router.exit()
    }

}