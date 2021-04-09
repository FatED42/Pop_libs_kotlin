package com.example.pop_libs_kotlin.mvp.model.entity.room

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    foreignKeys = [ForeignKey(
        entity = RoomGitHubUser::class,
        parentColumns = ["id"],
        childColumns = ["userId"],
        onDelete = ForeignKey.CASCADE
    )]
)
data class RoomGitHubUserRepos (
    @PrimaryKey val id: String,
    val name: String,
    val forksCount: Int,
    val watchersCount: Int,
    val language: String? = null,
    var userId: String
)