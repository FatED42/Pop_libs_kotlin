package com.example.pop_libs_kotlin.mvp.model.cache

import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single

interface IImageCache {
    fun putImage(url: String, bytes: ByteArray?) : Completable
    fun getImage(url: String) : Single<ByteArray>
}