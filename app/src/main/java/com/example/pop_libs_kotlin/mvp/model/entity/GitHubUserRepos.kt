package com.example.pop_libs_kotlin.mvp.model.entity

import android.os.Parcelable
import com.google.gson.annotations.Expose
import kotlinx.parcelize.Parcelize

@Parcelize
data class GitHubUserRepos (
    @Expose val id: String,
    @Expose val name: String,
    @Expose val forksCount: Int,
    @Expose val watchersCount: Int,
    @Expose val language: String?
) : Parcelable