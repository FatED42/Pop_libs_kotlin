package com.example.pop_libs_kotlin.mvp.model.repo

import com.example.pop_libs_kotlin.mvp.model.api.IDataSource
import com.example.pop_libs_kotlin.mvp.model.entity.GitHubUser
import io.reactivex.rxjava3.schedulers.Schedulers

class RetrofitGitHubUserRepos(private val api: IDataSource) : IGitHubUserRepos {
    override fun getRepos(user: GitHubUser) =
        api.getUserRepos(user.reposUrl).subscribeOn(Schedulers.io())
}