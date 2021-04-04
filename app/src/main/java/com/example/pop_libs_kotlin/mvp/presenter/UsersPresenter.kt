package com.example.pop_libs_kotlin.mvp.presenter

import com.example.pop_libs_kotlin.mvp.model.entity.GitHubUser
import com.example.pop_libs_kotlin.mvp.model.navigation.IScreens
import com.example.pop_libs_kotlin.mvp.model.repo.IGitHubUsersRepo
import com.example.pop_libs_kotlin.mvp.presenter.list.IUsersListPresenter
import com.example.pop_libs_kotlin.mvp.view.UsersView
import com.example.pop_libs_kotlin.mvp.view.list.IUserItemView
import com.github.terrakok.cicerone.Router
import io.reactivex.rxjava3.core.Scheduler
import moxy.MvpPresenter
import javax.inject.Inject
import javax.inject.Named

class UsersPresenter : MvpPresenter<UsersView>() {

    @Inject lateinit var usersRepo: IGitHubUsersRepo
    @Inject lateinit var screens: IScreens
    @Inject lateinit var router: Router
    @field:Named("ui") @Inject lateinit var uiScheduler: Scheduler

    class UsersListPresenter: IUsersListPresenter {
        val users = mutableListOf<GitHubUser>()

        override var itemClickListener: ((IUserItemView) -> Unit)? = null

        override fun bindView(view: IUserItemView) {
            val user = users[view.pos]
            view.setLogin(user.login)
            view.loadAvatar(user.avatarUrl)
        }

        override fun getCount(): Int = users.size

    }

    private val usersListPresenter = UsersListPresenter()

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.init()
        loadData()

        usersListPresenter.itemClickListener = { view ->
            val user = usersListPresenter.users[view.pos]
            router.navigateTo(screens.userPage(user))
        }
    }

    private fun loadData() {
        usersRepo.getUsers()
                .observeOn(uiScheduler)
                .subscribe({
                    usersListPresenter.users.clear()
                    usersListPresenter.users.addAll(it)
                    viewState.updateList()
                }, {
                    it.printStackTrace()
                })
    }

    fun backClicked(): Boolean {
        router.exit()
        return true
    }
}