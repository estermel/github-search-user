package com.ester.githubusersearch.data

import com.ester.githubusersearch.data.dto.UserDetailDto
import com.ester.githubusersearch.data.dto.UserSearchDto
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface GitHubAPI {
    @GET("/search/users")
    suspend fun search(
        @Query("q") query: String,
        @Query("page") page: Int,
        @Query("per_page") perPage: Int
    ): UserSearchDto

    @GET("/users")
    suspend fun getUserDetail(@Path("users") userLogin: String): UserDetailDto

}