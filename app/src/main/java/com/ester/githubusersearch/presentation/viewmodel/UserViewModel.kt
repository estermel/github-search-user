package com.ester.githubusersearch.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ester.githubusersearch.data.db.UserDb
import com.ester.githubusersearch.data.dto.UserDetailDto
import com.ester.githubusersearch.data.mapper.toUserDetailMap
import com.ester.githubusersearch.domain.Resource
import com.ester.githubusersearch.domain.model.UserDetailData
import com.ester.githubusersearch.domain.model.UserSearchData
import com.ester.githubusersearch.domain.repo.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor(
    private val db: UserDb,
    private val repository: UserRepository
) : ViewModel() {

    private val _userSearchList = MutableLiveData<UserSearchData?>()

    val userSearchList: MutableLiveData<UserSearchData?>
        get() = _userSearchList

    private val _userDetail = MutableLiveData<UserDetailData?>()
    val userDetail: MutableLiveData<UserDetailData?>
        get() = _userDetail

    private val _errorMessage = MutableLiveData<String?>()

    val errorMessage: MutableLiveData<String?>
        get() = _errorMessage

    fun search(username: String, page: Int, perPage: Int) {
        viewModelScope.launch {
            when (val result = repository.search(username, page, perPage)) {
                is Resource.Success -> {
                    _userSearchList.value = result.data
                }

                is Resource.Error -> _errorMessage.value = result.message
            }
        }
    }


    private fun getUserDetail(username: String) {
        viewModelScope.launch {
            when (val result = repository.getUserDetail(username)) {
                is Resource.Success -> {
                    _userDetail.value = result.data
                }

                is Resource.Error -> _errorMessage.value = result.message
            }
        }
    }

    fun getUserDetailCache(username: String) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                var errorThrown = true

                try {
                    val cache = db.userDetailDao.getUserDetail(username)
                    _userDetail.value = cache.toUserDetailMap()
                    errorThrown = false
                } catch (e: Exception) {
                    Log.e("E", e.toString())
                } finally {
                    if (errorThrown)
                        getUserDetail(username)
                }
            }
        }
    }
}