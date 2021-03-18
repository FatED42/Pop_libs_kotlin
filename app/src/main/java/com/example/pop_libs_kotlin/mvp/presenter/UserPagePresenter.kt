package com.example.pop_libs_kotlin.mvp.presenter

import com.example.pop_libs_kotlin.mvp.model.entity.GitHubUser
import com.example.pop_libs_kotlin.mvp.view.UserPageView
import com.github.terrakok.cicerone.Router
import moxy.MvpPresenter

class UserPagePresenter(private val user: GitHubUser, private val router: Router): MvpPresenter<UserPageView>() {

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.setLogin(user.login)
    }

    fun backClicked(): Boolean {
        router.exit()
        return true
    }

}