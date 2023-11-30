package com.ester.githubusersearch.data

import com.squareup.moshi.Json

data class UserDetailDto(
    @field:Json(name = "login")
    val login: String,
    @field:Json(name = "avatar_url")
    val avatarUrl: String,
    @field:Json(name = "html_url")
    val htmlUrl: String,
    @field:Json(name = "name")
    val name: String,
    @field:Json(name = "company")
    val company: String,
    @field:Json(name = "blog")
    val blog: String,
    @field:Json(name = "location")
    val location: String,
    @field:Json(name = "email")
    val email: String,
    @field:Json(name = "bio")
    val bio: String,
    @field:Json(name = "twitter_username")
    val twitterUsername: String,
    @field:Json(name = "public_repos")
    val publicRepos: Int,
    @field:Json(name = "public_gists")
    val publicGists: Int,
    @field:Json(name = "followers")
    val followers: Int,
    @field:Json(name = "following")
    val following: Int
)
