package com.ester.githubusersearch.domain.repo

import com.ester.githubusersearch.domain.Resource
import com.ester.githubusersearch.domain.model.UserDetailData
import com.ester.githubusersearch.domain.model.UserSearchData

interface UserRepository {

    suspend fun search(username: String, page: Int, perPage: Int): Resource<UserSearchData>

    suspend fun getUserDetail(username: String): Resource<UserDetailData>
}