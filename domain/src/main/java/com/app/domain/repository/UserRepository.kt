package com.app.domain.repository

import com.app.common.Resource
import com.app.domain.entity.UserDetailEntityModel
import com.app.domain.entity.UserEntityModel
import kotlinx.coroutines.flow.Flow

interface UserRepository {
    suspend fun getUsersPaging(pageSize: Int) : List<UserEntityModel>

    suspend fun getUser(name: String) : Flow<Resource<UserDetailEntityModel>>

    suspend fun getUserFollowersCount(name: String) : Flow<Resource<Int>>

    suspend fun getUserRepositoryCount(name: String) : Flow<Resource<Int>>
}