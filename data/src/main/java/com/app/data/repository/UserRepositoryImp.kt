package com.app.data.repository

import com.app.common.Mapper
import com.app.common.Resource
import com.app.data.model.UserDataModel
import com.app.data.model.UserDetailDataModel
import com.app.domain.entity.UserDetailEntityModel
import com.app.domain.entity.UserEntityModel
import com.app.domain.repository.UserRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class UserRepositoryImp @Inject constructor(
    private val remoteUserSource: RemoteUserSource,
    private val userMapper: Mapper<UserDataModel, UserEntityModel>,
    private val userDetailMapper: Mapper<UserDetailDataModel, UserDetailEntityModel>
) : UserRepository {

    override suspend fun getUsersPaging(pageSize: Int): List<UserEntityModel> {
        return userMapper.fromList(remoteUserSource.getUsers(pageSize))
    }

    override suspend fun getUser(name: String): Flow<Resource<UserDetailEntityModel>> {
        return flow {
            try {
                val user = remoteUserSource.getUser(name)
                emit(Resource.Success(userDetailMapper.from(user)))
            } catch (ex: Exception) {
                emit(Resource.Error(ex))
            }
        }
    }

    override suspend fun getUserFollowersCount(name: String): Flow<Resource<Int>> {
        return flow {
            try {
                val followersCount = remoteUserSource.getUserFollowers(name).size
                emit(Resource.Success(followersCount))
            } catch (ex: Exception) {
                emit(Resource.Error(ex))
            }
        }
    }

    override suspend fun getUserRepositoryCount(name: String): Flow<Resource<Int>> {
        return flow {
            try {
                val user = remoteUserSource.getUserRepositoryCount(name)
                emit(Resource.Success(user.size))
            } catch (ex: Exception) {
                emit(Resource.Error(ex))
            }
        }
    }
}