package com.ester.githubusersearch.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.ester.githubusersearch.data.dto.UserDetailDto

@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(userDetail: UserDetailDto): Long

    @Query("SELECT * FROM user_detail WHERE login IS :username")
    suspend fun getUserDetail(username: String): UserDetailDto

    @Query("SELECT (SELECT COUNT(*) FROM user_detail WHERE login IS :username) == 0")
    fun isUserDetailNotFound(username: String): Boolean
}