package com.example.pop_libs_kotlin.di.module

import androidx.room.Room
import com.example.pop_libs_kotlin.mvp.model.cache.*
import com.example.pop_libs_kotlin.mvp.model.entity.room.db.Database
import com.example.pop_libs_kotlin.ui.App
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class CacheModule {

    @Provides
    @Singleton
    fun database(app: App) = Room.databaseBuilder(app, Database::class.java, Database.DB_NAME)
        .build()

    @Provides
    @Singleton
    fun usersCache(db: Database): IGitHubUsersCache = GitHubUsersCache(db)

    @Provides
    @Singleton
    fun reposCache(db: Database): IGitHubUserReposCache = GitHubUserReposCache(db)

    @Provides
    @Singleton
    fun imageCache(app: App, db: Database): IImageCache = ImageCache(app, db)

}