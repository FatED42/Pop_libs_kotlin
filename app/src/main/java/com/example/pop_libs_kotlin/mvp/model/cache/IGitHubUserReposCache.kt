package com.example.pop_libs_kotlin.mvp.model.cache

import com.example.pop_libs_kotlin.mvp.model.entity.GitHubUser
import com.example.pop_libs_kotlin.mvp.model.entity.GitHubUserRepos
import io.reactivex.rxjava3.core.Single

interface IGitHubUserReposCache {
    fun putRepos(user: GitHubUser, repos: List<GitHubUserRepos>)
    fun getRepos(user: GitHubUser) : Single<List<GitHubUserRepos>>
}