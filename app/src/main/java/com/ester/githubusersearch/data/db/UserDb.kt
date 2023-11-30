package com.ester.githubusersearch.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.ester.githubusersearch.data.dto.UserDetailDto

@Database(entities = [UserDetailDto::class], version = 1, exportSchema = false)
abstract class UserDb : RoomDatabase() {

    abstract val userDetailDao: UserDao

}