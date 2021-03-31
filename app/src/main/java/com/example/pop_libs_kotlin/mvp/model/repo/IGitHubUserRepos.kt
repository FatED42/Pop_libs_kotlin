package com.example.pop_libs_kotlin.mvp.model.repo

import com.example.pop_libs_kotlin.mvp.model.entity.GitHubUser
import com.example.pop_libs_kotlin.mvp.model.entity.GitHubUserRepos
import io.reactivex.rxjava3.core.Single

interface IGitHubUserRepos {
    fun getRepos(user: GitHubUser): Single<List<GitHubUserRepos>>
}