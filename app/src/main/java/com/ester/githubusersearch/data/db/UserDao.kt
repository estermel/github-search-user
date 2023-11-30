package com.ester.githubusersearch.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.ester.githubusersearch.data.dto.UserDetailDto
import kotlinx.coroutines.CompletableJob
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(currencies: MutableList<UserDetailDto>): CompletableJob

    @Query("SELECT * FROM user_detail WHERE login IS :username")
    suspend fun getUserDetail(username: String): Flow<UserDetailDto>

    @Query("SELECT (SELECT COUNT(*) FROM user_detail WHERE login IS :username) == 0")
    fun isUserDetailNotFound(username: String): Boolean
}