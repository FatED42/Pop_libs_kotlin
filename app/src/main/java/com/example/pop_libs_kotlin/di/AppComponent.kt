package com.example.pop_libs_kotlin.di

import com.example.pop_libs_kotlin.di.module.*
import com.example.pop_libs_kotlin.mvp.presenter.MainPresenter
import com.example.pop_libs_kotlin.mvp.presenter.UserPagePresenter
import com.example.pop_libs_kotlin.mvp.presenter.UsersPresenter
import com.example.pop_libs_kotlin.ui.activity.MainActivity
import com.example.pop_libs_kotlin.ui.adapter.UsersRVAdapter
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AppModule::class,
        CiceroneModule::class,
        ApiModule::class,
        RepoModule::class,
        CacheModule::class,
        ImageModule::class
    ]
)
interface AppComponent {
    fun inject(mainActivity: MainActivity)
    fun inject(mainPresenter: MainPresenter)
    fun inject(usersPresenter: UsersPresenter)
    fun inject(userPagePresenter: UserPagePresenter)
    fun inject(usersRVAdapter: UsersRVAdapter)
}