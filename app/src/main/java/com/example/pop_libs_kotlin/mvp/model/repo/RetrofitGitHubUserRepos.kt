package com.example.pop_libs_kotlin.mvp.model.repo

import com.example.pop_libs_kotlin.mvp.model.api.IDataSource
import com.example.pop_libs_kotlin.mvp.model.cache.IGitHubUserReposCache
import com.example.pop_libs_kotlin.mvp.model.entity.GitHubUser
import com.example.pop_libs_kotlin.mvp.model.entity.GitHubUserRepos
import com.example.pop_libs_kotlin.mvp.model.entity.room.RoomGitHubUserRepos
import com.example.pop_libs_kotlin.mvp.model.entity.room.db.Database
import com.example.pop_libs_kotlin.mvp.model.network.INetworkStatus
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import java.lang.RuntimeException

class RetrofitGitHubUserRepos(
    private val api: IDataSource,
    private val networkStatus: INetworkStatus,
    private val userRepos: IGitHubUserReposCache
) : IGitHubUserRepos {

    override fun getRepos(user: GitHubUser) = networkStatus.isOnlineSingle().flatMap { isOnline ->
        if (isOnline) {
            user.reposUrl?.let { url ->
                api.getUserRepos(url)
                    .doAfterSuccess { repositories ->
                        userRepos.putRepos(user, repositories)
                    }

            } ?: Single.error(RuntimeException("User has no repos url"))
        } else userRepos.getRepos(user)
    }.subscribeOn(Schedulers.io())

}