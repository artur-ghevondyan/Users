package com.app.domain.usecase

abstract class BasePagerUseCase<Model, Params> {
    abstract suspend fun buildRequest(page: Int): Model
}