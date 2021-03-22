package com.example.pop_libs_kotlin.mvp.model.repo

import com.example.pop_libs_kotlin.mvp.model.entity.GitHubUser
import io.reactivex.rxjava3.core.Single

interface IGitHubUsersRepo {
    fun getUsers(): Single<List<GitHubUser>>
}