package com.example.pop_libs_kotlin.mvp.model.api

import com.example.pop_libs_kotlin.mvp.model.entity.GitHubUser
import com.example.pop_libs_kotlin.mvp.model.entity.GitHubUserRepos
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Url

interface IDataSource {

    @GET("users")
    fun getUsers() : Single<List<GitHubUser>>

    @GET
    fun getUserRepos(@Url url: String) : Single<List<GitHubUserRepos>>
}