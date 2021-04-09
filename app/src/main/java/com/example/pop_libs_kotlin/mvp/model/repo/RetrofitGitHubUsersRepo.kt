package com.example.pop_libs_kotlin.mvp.model.repo

import com.example.pop_libs_kotlin.mvp.model.api.IDataSource
import com.example.pop_libs_kotlin.mvp.model.cache.IGitHubUsersCache
import com.example.pop_libs_kotlin.mvp.model.entity.GitHubUser
import com.example.pop_libs_kotlin.mvp.model.entity.room.RoomGitHubUser
import com.example.pop_libs_kotlin.mvp.model.entity.room.db.Database
import com.example.pop_libs_kotlin.mvp.model.network.INetworkStatus
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers

class RetrofitGitHubUsersRepo(
    private val api: IDataSource,
    private val networkStatus: INetworkStatus,
    private val usersCache: IGitHubUsersCache
) : IGitHubUsersRepo {

    override fun getUsers() = networkStatus.isOnlineSingle().flatMap { isOnline ->
        if (isOnline) {
            api.getUsers()
                    .doAfterSuccess { users ->
                        usersCache.putUsers(users)
                    }
        } else usersCache.getUsers()
    }.subscribeOn(Schedulers.io())

}