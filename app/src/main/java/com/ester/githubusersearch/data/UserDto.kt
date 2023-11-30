package com.ester.githubusersearch.data

import com.squareup.moshi.Json

data class UserDto(
    @field:Json(name = "login")
    val login: String,
    @field:Json(name = "avatar_url")
    val avatarUrl: String
)
