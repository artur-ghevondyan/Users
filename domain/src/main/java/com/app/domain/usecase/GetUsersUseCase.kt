package com.app.domain.usecase

import com.app.domain.entity.UserEntityModel
import com.app.domain.repository.UserRepository
import javax.inject.Inject

class GetUsersUseCase  @Inject constructor(
    private val repository: UserRepository
) : BasePagerUseCase<List<UserEntityModel>, Int>() {

    override suspend fun buildRequest(page: Int): List<UserEntityModel> {
        return repository.getUsersPaging(page)
    }
}