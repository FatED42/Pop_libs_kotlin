package com.example.pop_libs_kotlin.mvp.model

import com.example.pop_libs_kotlin.mvp.model.entity.GitHubUser

class GitHubUsersRepo {
    private val users = listOf<GitHubUser>(
        GitHubUser("login1"),
        GitHubUser("login2"),
        GitHubUser("login3"),
        GitHubUser("login4"),
        GitHubUser("login5"),
        GitHubUser("login6"),
        GitHubUser("login7"),
        GitHubUser("login8")
    )

    fun getUsers(): List<GitHubUser> {
        return users
    }
}