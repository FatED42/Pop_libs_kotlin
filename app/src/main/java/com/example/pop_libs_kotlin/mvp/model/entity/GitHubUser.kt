package com.example.pop_libs_kotlin.mvp.model.entity

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class GitHubUser(
        val login: String
): Parcelable
