package com.example.pop_libs_kotlin.mvp.model.cache

import com.example.pop_libs_kotlin.mvp.model.entity.GitHubUser
import io.reactivex.rxjava3.core.Single

interface IGitHubUsersCache {
    fun putUsers(users: List<GitHubUser>)
    fun getUsers() : Single<List<GitHubUser>>
}