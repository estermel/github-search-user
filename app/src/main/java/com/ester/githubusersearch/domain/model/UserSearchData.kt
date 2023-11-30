package com.ester.githubusersearch.domain.model

data class UserSearchData(
    val totalCount: Int,
    val items: List<UserData>
)
