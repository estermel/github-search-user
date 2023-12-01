package com.ester.githubusersearch.data.repoimpl

import com.ester.githubusersearch.data.GitHubAPI
import com.ester.githubusersearch.data.db.UserDb
import com.ester.githubusersearch.data.mapper.toUserDetailMap
import com.ester.githubusersearch.data.mapper.toUserSearchMap
import com.ester.githubusersearch.domain.Resource
import com.ester.githubusersearch.domain.model.UserDetailData
import com.ester.githubusersearch.domain.model.UserSearchData
import com.ester.githubusersearch.domain.repo.UserRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val api: GitHubAPI,
    private val db: UserDb
): UserRepository {
    override suspend fun search(
        username: String,
        page: Int,
        perPage: Int
    ): Resource<UserSearchData> {
        return try {
            val response = api.search(username, page, perPage)
            Resource.Success(data = response.toUserSearchMap())
        } catch (e: Exception) {
            e.printStackTrace()
            Resource.Error(e.message ?: "An unknown error occurred.")
        }
    }

    override suspend fun getUserDetail(username: String): Resource<UserDetailData> {
        return try {
            val response = api.getUserDetail(username)
            withContext(Dispatchers.IO) {
                db.userDetailDao.insert(response)
            }
            Resource.Success(data = response.toUserDetailMap())
        } catch (e: Exception) {
            e.printStackTrace()
            Resource.Error(e.message ?: "An unknown error occurred.")
        }
    }
}