package com.ester.githubusersearch.data.mapper

import com.ester.githubusersearch.data.dto.UserDetailDto
import com.ester.githubusersearch.data.dto.UserSearchDto
import com.ester.githubusersearch.domain.model.UserData
import com.ester.githubusersearch.domain.model.UserDetailData
import com.ester.githubusersearch.domain.model.UserSearchData


fun UserSearchDto.toUserSearchMap(): UserSearchData {
    return UserSearchData(
        totalCount, items = List(items.size) { index ->
            UserData(
                items[index].login,
                items[index].avatarUrl
            )
        }
    )
}

fun UserDetailDto.toUserDetailMap(): UserDetailData {
    return UserDetailData(
        login,
        avatarUrl,
        htmlUrl,
        name,
        company,
        blog,
        location,
        email,
        bio,
        twitterUsername,
        publicRepos,
        publicGists,
        followers,
        following
    )
}