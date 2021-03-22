package com.example.pop_libs_kotlin.ui.navigation

import com.example.pop_libs_kotlin.mvp.model.entity.GitHubUser
import com.example.pop_libs_kotlin.mvp.model.navigation.IScreens
import com.example.pop_libs_kotlin.ui.fragment.UserPageFragment
import com.example.pop_libs_kotlin.ui.fragment.UsersFragment
import com.github.terrakok.cicerone.androidx.FragmentScreen

class AndroidScreens: IScreens {
    override fun users() = FragmentScreen { UsersFragment.newInstance() }
    override fun userPage(user: GitHubUser) = FragmentScreen { UserPageFragment.newInstance(user) }
}