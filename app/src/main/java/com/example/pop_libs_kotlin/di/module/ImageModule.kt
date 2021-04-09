package com.example.pop_libs_kotlin.di.module

import android.widget.ImageView
import com.example.pop_libs_kotlin.mvp.model.cache.IImageCache
import com.example.pop_libs_kotlin.mvp.model.image.IImageLoader
import com.example.pop_libs_kotlin.mvp.model.network.INetworkStatus
import com.example.pop_libs_kotlin.ui.image.GlideImageLoader
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class ImageModule {

    @Singleton
    @Provides
    fun glideImageLoader(
            imageCache: IImageCache,
            networkStatus: INetworkStatus
    ) : IImageLoader<ImageView> = GlideImageLoader(networkStatus, imageCache)

}