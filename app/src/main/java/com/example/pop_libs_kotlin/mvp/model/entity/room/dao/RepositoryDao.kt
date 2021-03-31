package com.example.pop_libs_kotlin.mvp.model.entity.room.dao

import androidx.room.*
import com.example.pop_libs_kotlin.mvp.model.entity.room.RoomGitHubUser
import com.example.pop_libs_kotlin.mvp.model.entity.room.RoomGitHubUserRepos

@Dao
interface RepositoryDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(repository: RoomGitHubUserRepos)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(vararg repositories: RoomGitHubUserRepos)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(repositories: List<RoomGitHubUserRepos>)

    @Update
    fun update(repository: RoomGitHubUserRepos)

    @Update
    fun update(vararg repositories: RoomGitHubUserRepos)

    @Update
    fun update(repositories: List<RoomGitHubUserRepos>)

    @Delete
    fun delete(repository: RoomGitHubUserRepos)

    @Delete
    fun delete(vararg repositories: RoomGitHubUserRepos)

    @Delete
    fun delete(repositories: List<RoomGitHubUserRepos>)

    @Query("SELECT * FROM RoomGitHubUserRepos")
    fun getAll() : List<RoomGitHubUserRepos>

    @Query("SELECT * FROM RoomGitHubUserRepos WHERE userId = :userId")
    fun findForUser(userId: String) : List<RoomGitHubUserRepos>

}