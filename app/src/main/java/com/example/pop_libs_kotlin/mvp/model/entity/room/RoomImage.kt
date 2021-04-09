package com.example.pop_libs_kotlin.mvp.model.entity.room

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class RoomImage (
    @PrimaryKey var url: String,
    var localPath: String?
)