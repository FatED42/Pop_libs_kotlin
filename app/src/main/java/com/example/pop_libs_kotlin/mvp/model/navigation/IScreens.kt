package com.example.pop_libs_kotlin.mvp.model.navigation

import com.example.pop_libs_kotlin.mvp.model.entity.GitHubUser
import com.github.terrakok.cicerone.Screen

interface IScreens {
    fun users(): Screen
    fun userPage(user: GitHubUser): Screen
}