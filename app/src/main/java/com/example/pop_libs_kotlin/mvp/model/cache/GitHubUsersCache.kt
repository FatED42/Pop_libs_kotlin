package com.example.pop_libs_kotlin.mvp.model.cache

import com.example.pop_libs_kotlin.mvp.model.entity.GitHubUser
import com.example.pop_libs_kotlin.mvp.model.entity.room.RoomGitHubUser
import com.example.pop_libs_kotlin.mvp.model.entity.room.db.Database
import io.reactivex.rxjava3.core.Single

class GitHubUsersCache(private val db: Database) : IGitHubUsersCache {
    override fun putUsers(users: List<GitHubUser>) {
        Single.fromCallable {
            val roomUsers = users.map { user ->
                RoomGitHubUser(user.id, user.login, user.avatarUrl, user.reposUrl)
            }
            db.userDao.insert(roomUsers)
        }
    }

    override fun getUsers() = Single.fromCallable {
            db.userDao.getAll().map { roomUser ->
                GitHubUser(roomUser.id, roomUser.login, roomUser.avatarUrl, roomUser.reposUrl)
            }
        }

}