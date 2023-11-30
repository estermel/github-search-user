package com.ester.githubusersearch.domain

data class UserSearchData(
    val totalCount: Int,
    val items: List<UserData>
)
