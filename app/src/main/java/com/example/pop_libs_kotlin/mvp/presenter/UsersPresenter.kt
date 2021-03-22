package com.example.pop_libs_kotlin.mvp.presenter

import com.example.pop_libs_kotlin.mvp.model.entity.GitHubUser
import com.example.pop_libs_kotlin.mvp.model.navigation.IScreens
import com.example.pop_libs_kotlin.mvp.model.repo.IGitHubUsersRepo
import com.example.pop_libs_kotlin.mvp.presenter.list.IUsersListPresenter
import com.example.pop_libs_kotlin.mvp.view.UsersView
import com.example.pop_libs_kotlin.mvp.view.list.IUserItemView
import com.github.terrakok.cicerone.Router
import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.disposables.CompositeDisposable
import moxy.MvpPresenter

class UsersPresenter(
    private val usersRepo: IGitHubUsersRepo,
    private val router: Router,
    private val screens: IScreens,
    private val uiScheduler: Scheduler
        ) : MvpPresenter<UsersView>() {

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

    val usersListPresenter = UsersListPresenter()

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