package com.app.data.repository

import com.app.data.model.UserDataModel
import com.app.data.model.UserDetailDataModel

interface RemoteUserSource  {
    suspend fun getUsers(since: Int, per_page: Int = 20) : List<UserDataModel>

    suspend fun getUserRepositoryCount(userName: String) : List<Any?>

    suspend fun getUser(name: String) : UserDetailDataModel

    suspend fun getUserFollowers(name: String) : List<UserDataModel>
}