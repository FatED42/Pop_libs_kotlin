package com.example.pop_libs_kotlin.mvp.model.repo

import com.example.pop_libs_kotlin.mvp.model.api.IDataSource
import com.example.pop_libs_kotlin.mvp.model.entity.GitHubUser
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.schedulers.Schedulers

class RetrofitGitHubUsersRepo(private val api: IDataSource): IGitHubUsersRepo {
    override fun getUsers() = api.getUsers().subscribeOn(Schedulers.io())
}