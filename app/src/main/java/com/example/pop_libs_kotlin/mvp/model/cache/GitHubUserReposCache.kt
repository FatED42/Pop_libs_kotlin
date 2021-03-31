package com.example.pop_libs_kotlin.mvp.model.cache

import com.example.pop_libs_kotlin.mvp.model.entity.GitHubUser
import com.example.pop_libs_kotlin.mvp.model.entity.GitHubUserRepos
import com.example.pop_libs_kotlin.mvp.model.entity.room.RoomGitHubUserRepos
import com.example.pop_libs_kotlin.mvp.model.entity.room.db.Database
import io.reactivex.rxjava3.core.Single
import java.lang.RuntimeException

class GitHubUserReposCache(private val db: Database) : IGitHubUserReposCache {

    override fun putRepos(user: GitHubUser, repos: List<GitHubUserRepos>) {
        Single.fromCallable {
            val roomUser = db.userDao.findByLogin(user.login) ?: throw RuntimeException("No user in DB")
            val roomRepos = repos.map { repo ->
                RoomGitHubUserRepos(repo.id, repo.name, repo.forksCount, repo.watchersCount, repo.language, roomUser.id)
            }
            db.repositoryDao.insert(roomRepos)
        }
    }

    override fun getRepos(user: GitHubUser) = Single.fromCallable {
            val roomUser = db.userDao.findByLogin(user.login) ?: throw RuntimeException("No user in DB")
            db.repositoryDao.findForUser(roomUser.id).map { roomRepo ->
                GitHubUserRepos(roomRepo.id, roomRepo.name, roomRepo.forksCount, roomRepo.watchersCount, roomRepo.language)
            }
    }

}
