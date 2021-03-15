package com.example.pop_libs_kotlin.mvp.model

import com.example.pop_libs_kotlin.mvp.model.entity.GitHubUser
import io.reactivex.rxjava3.core.Observable

class GitHubUsersRepo {
    private val users = listOf(
        GitHubUser("login1"),
        GitHubUser("login2"),
        GitHubUser("login3"),
        GitHubUser("login4"),
        GitHubUser("login5"),
        GitHubUser("login6"),
        GitHubUser("login7"),
        GitHubUser("login8")
    )

    fun getUsers(): Observable<List<GitHubUser>> {
        return Observable.just(users)
    }
}