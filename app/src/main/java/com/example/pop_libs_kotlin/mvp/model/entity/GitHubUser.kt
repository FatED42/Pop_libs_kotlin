package com.example.pop_libs_kotlin.mvp.model.entity

import android.os.Parcelable
import com.google.gson.annotations.Expose
import kotlinx.parcelize.Parcelize

@Parcelize
data class GitHubUser(
        @Expose val login: String,
        @Expose val avatarUrl: String,
        @Expose val reposUrl: String
) : Parcelable
