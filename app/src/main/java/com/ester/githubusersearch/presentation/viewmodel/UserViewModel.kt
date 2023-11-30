package com.ester.githubusersearch.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ester.githubusersearch.data.db.UserDb
import com.ester.githubusersearch.domain.Resource
import com.ester.githubusersearch.domain.model.UserDetailData
import com.ester.githubusersearch.domain.model.UserSearchData
import com.ester.githubusersearch.domain.repo.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor(
    private val db: UserDb,
    private val repository: UserRepository
) : ViewModel() {

    private var _userSearchList: UserSearchData? = null
    val userSearchList: UserSearchData?
        get() = _userSearchList

    private var _userDetail: UserDetailData? = null
    val userDetail: UserDetailData?
        get() = _userDetail

    private var _errorMessage: String? = null
    val errorMessage: String?
        get() = _errorMessage

    fun search(username: String, page: Int, perPage: Int) {
        viewModelScope.launch {
            when (val result = repository.search(username, page, perPage)) {
                is Resource.Success -> _userSearchList = result.data
                is Resource.Error -> _errorMessage = result.message
            }
        }
    }

    fun getUserDetail(username: String) {
        viewModelScope.launch {
            when (val result = repository.getUserDetail(username)) {
                is Resource.Success -> _userDetail = result.data
                is Resource.Error -> _errorMessage = result.message
            }
        }
    }
}