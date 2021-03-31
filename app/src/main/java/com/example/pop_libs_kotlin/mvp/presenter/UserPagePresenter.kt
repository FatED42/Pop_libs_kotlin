package com.example.pop_libs_kotlin.mvp.presenter

import com.example.pop_libs_kotlin.mvp.model.entity.GitHubUser
import com.example.pop_libs_kotlin.mvp.model.entity.GitHubUserRepos
import com.example.pop_libs_kotlin.mvp.model.repo.IGitHubUserRepos
import com.example.pop_libs_kotlin.mvp.presenter.list.IUserReposListPresenter
import com.example.pop_libs_kotlin.mvp.view.UserPageView
import com.example.pop_libs_kotlin.mvp.view.list.IUserReposItemView
import com.github.terrakok.cicerone.Router
import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.disposables.CompositeDisposable
import moxy.MvpPresenter

class UserPagePresenter(
    private val user: GitHubUser,
    private val router: Router,
    private val uiScheduler: Scheduler,
    private val repos: IGitHubUserRepos
    ): MvpPresenter<UserPageView>() {

    class UserReposListPresenter : IUserReposListPresenter {
        val userRepos = mutableListOf<GitHubUserRepos>()
        override var itemClickListener: ((IUserReposItemView) -> Unit)? = null

        override fun bindView(view: IUserReposItemView) {
            val repos = userRepos[view.pos]
            view.setReposName(repos.name)
        }

        override fun getCount() = userRepos.size
    }

    val userReposListPresenter = UserReposListPresenter()
    private val compositeDisposable = CompositeDisposable()

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.init()
        viewState.setLogin(user.login)
        viewState.setImage(user.avatarUrl)
        loadRepos()

        userReposListPresenter.itemClickListener = { view ->
            val forksCount = userReposListPresenter.userRepos[view.pos]
            val viewsCount = userReposListPresenter.userRepos[view.pos]
            val language = userReposListPresenter.userRepos[view.pos]
            viewForksCount(forksCount.forksCount, viewsCount.watchersCount, language.language)
        }
    }

    private fun viewForksCount(scoreFork: Int, scoreViews: Int, language: String?) {
        if (language != null) {
            viewState.showRepoInfo(scoreFork, scoreViews, language)
        } else viewState.showRepoInfo(scoreFork, scoreViews, "no info")
    }

    private fun loadRepos() {
        val disposable = repos.getRepos(user)
            .observeOn(uiScheduler)
            .subscribe({ reposList ->
                userReposListPresenter.userRepos.addAll(reposList)
                viewState.updateReposList()
            }, { error ->
                error.printStackTrace()
            })
        compositeDisposable.add(disposable)
    }

    fun backClick(): Boolean {
        router.exit()
        return true
    }
}