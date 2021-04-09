package com.example.pop_libs_kotlin.di.module

import com.example.pop_libs_kotlin.mvp.model.navigation.IScreens
import com.example.pop_libs_kotlin.ui.navigation.AndroidScreens
import com.github.terrakok.cicerone.Cicerone
import com.github.terrakok.cicerone.NavigatorHolder
import com.github.terrakok.cicerone.Router
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class CiceroneModule {

    private val cicerone: Cicerone<Router> = Cicerone.create()

    @Provides
    fun navigatorHolder() : NavigatorHolder = cicerone.getNavigatorHolder()

    @Provides
    fun router() : Router = cicerone.router

    @Provides
    @Singleton
    fun screens() : IScreens = AndroidScreens()
}