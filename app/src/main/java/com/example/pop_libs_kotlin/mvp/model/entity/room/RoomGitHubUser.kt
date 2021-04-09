package com.example.pop_libs_kotlin.mvp.model.entity.room

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class RoomGitHubUser (
    @PrimaryKey val id: String,
    val login: String,
    val avatarUrl: String,
    val reposUrl: String?
)