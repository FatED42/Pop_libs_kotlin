package com.example.pop_libs_kotlin.mvp.model.entity.room.db

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.pop_libs_kotlin.mvp.model.entity.room.RoomGitHubUser
import com.example.pop_libs_kotlin.mvp.model.entity.room.RoomGitHubUserRepos
import com.example.pop_libs_kotlin.mvp.model.entity.room.RoomImage
import com.example.pop_libs_kotlin.mvp.model.entity.room.dao.ImageDao
import com.example.pop_libs_kotlin.mvp.model.entity.room.dao.RepositoryDao
import com.example.pop_libs_kotlin.mvp.model.entity.room.dao.UserDao

@androidx.room.Database(
    entities = [
        RoomGitHubUser::class,
        RoomGitHubUserRepos::class,
        RoomImage::class
    ],
    version = 1
)
abstract class Database : RoomDatabase() {

    abstract val userDao: UserDao
    abstract val repositoryDao: RepositoryDao
    abstract val imageDao: ImageDao

    companion object {
        const val DB_NAME = "database.db"
        private var instance: Database? = null
        fun getInstance() = instance ?: throw IllegalStateException("Database has not been created")
        fun create(context: Context) {
            if (instance == null) {
                instance = Room.databaseBuilder(context, Database::class.java, DB_NAME).build()
            }
        }
    }
}