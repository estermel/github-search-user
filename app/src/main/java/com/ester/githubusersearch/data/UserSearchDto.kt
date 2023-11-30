package com.ester.githubusersearch.data

import com.squareup.moshi.Json

data class UserSearchDto(
    @field:Json(name = "total_count")
    val totalCount: Int,
    @field:Json(name = "items")
    val items: List<UserDto>
)
