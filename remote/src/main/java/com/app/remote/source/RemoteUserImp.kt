package com.app.remote.source

import com.app.common.Mapper
import com.app.data.model.UserDataModel
import com.app.data.model.UserDetailDataModel
import com.app.data.repository.RemoteUserSource
import com.app.remote.api.ApiService
import com.app.remote.model.UserDetailRemoteModel
import com.app.remote.model.UserRemoteModel
import javax.inject.Inject

class RemoteUserImp @Inject constructor(
    private val apiService: ApiService,
    private val getUserMapper: Mapper<UserRemoteModel, UserDataModel>,
    private val getDetailUserMapper: Mapper<UserDetailRemoteModel, UserDetailDataModel>
) : RemoteUserSource {

    override suspend fun getUsers(since: Int, per_page: Int): List<UserDataModel> {
        return getUserMapper.fromList(apiService.getUsers(since, per_page))
    }

    override suspend fun getUserRepositoryCount(userName: String): List<Any?> {
        return apiService.getUserRepositoryCount(userName)
    }

    override suspend fun getUser(name: String): UserDetailDataModel {
        return getDetailUserMapper.from(apiService.getUser(name))
    }

    override suspend fun getUserFollowers(name: String): List<UserDataModel> {
        val userList = apiService.getUserFollowers(name)
        return getUserMapper.fromList(userList)
    }
}