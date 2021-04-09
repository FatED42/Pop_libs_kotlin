package com.example.pop_libs_kotlin.mvp.model.entity.room.dao

import androidx.room.*
import com.example.pop_libs_kotlin.mvp.model.entity.room.RoomImage

@Dao
interface ImageDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(image: RoomImage)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(vararg images: RoomImage)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(image: List<RoomImage>)

    @Update
    fun update(image: RoomImage)

    @Update
    fun update(vararg images: RoomImage)

    @Update
    fun update(image: List<RoomImage>)

    @Delete
    fun delete(image: RoomImage)

    @Delete
    fun delete(vararg image: RoomImage)

    @Delete
    fun delete(images: List<RoomImage>)

    @Query("SELECT * FROM RoomImage")
    fun getAll(): List<RoomImage>

    @Query("SELECT * FROM RoomImage WHERE url = :url LIMIT 1")
    fun findByUrl(url: String): RoomImage?
}