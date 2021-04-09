package com.example.pop_libs_kotlin.di.module

import com.example.pop_libs_kotlin.mvp.model.api.IDataSource
import com.example.pop_libs_kotlin.mvp.model.cache.IGitHubUserReposCache
import com.example.pop_libs_kotlin.mvp.model.cache.IGitHubUsersCache
import com.example.pop_libs_kotlin.mvp.model.network.INetworkStatus
import com.example.pop_libs_kotlin.mvp.model.repo.IGitHubUserRepos
import com.example.pop_libs_kotlin.mvp.model.repo.IGitHubUsersRepo
import com.example.pop_libs_kotlin.mvp.model.repo.RetrofitGitHubUserRepos
import com.example.pop_libs_kotlin.mvp.model.repo.RetrofitGitHubUsersRepo
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RepoModule {

    @Singleton
    @Provides
    fun usersRepo(
        api: IDataSource,
        networkStatus: INetworkStatus,
        usersCache: IGitHubUsersCache
    ) : IGitHubUsersRepo = RetrofitGitHubUsersRepo(api, networkStatus, usersCache)

    @Singleton
    @Provides
    fun reposRepo(
            api: IDataSource,
            networkStatus: INetworkStatus,
            reposCache: IGitHubUserReposCache
    ) : IGitHubUserRepos = RetrofitGitHubUserRepos(api, networkStatus, reposCache)

}