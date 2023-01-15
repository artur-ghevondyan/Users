package com.app.domain.usecase

import com.app.common.Resource
import com.app.domain.entity.UserDetailEntityModel
import com.app.domain.qualifiers.IoDispatcher
import com.app.domain.repository.UserRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class GetUserUseCase @Inject constructor(
    private val repository: UserRepository,
    @IoDispatcher private val dispatcher: CoroutineDispatcher
) : BaseUseCase<UserDetailEntityModel, String>() {
    override suspend fun buildRequest(params: String?): Flow<Resource<UserDetailEntityModel>> {
        if (params == null) {
            return flow {
                emit(Resource.Error(Exception("Name can not be null")))
            }.flowOn(dispatcher)
        }
        return repository.getUser(params).flowOn(dispatcher)
    }
}