package com.example.pop_libs_kotlin.mvp.model.image

interface IImageLoader<T> {
    fun load(url: String, container: T)
}