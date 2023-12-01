package com.ester.githubusersearch.domain.model

data class UserDetailData(
    val login: String,
    val avatarUrl: String,
    val htmlUrl: String,
    val name: String? = "",
    val company: String? = "",
    val blog: String? = "",
    val location: String? = "",
    val bio: String? = "",
    val twitterUsername: String? = "",
    val publicRepos: Int,
    val publicGists: Int,
    val followers: Int,
    val following: Int
)
