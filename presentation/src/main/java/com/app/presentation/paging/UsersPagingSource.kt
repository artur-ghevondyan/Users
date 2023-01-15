package com.app.presentation.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.app.common.Mapper
import com.app.domain.entity.UserEntityModel
import com.app.domain.usecase.GetUsersUseCase
import com.app.presentation.model.UserUiModel

class UsersPagingSource(
    private val getUsersUseCase: GetUsersUseCase,
    private val mapper: Mapper<UserEntityModel, UserUiModel>
) : PagingSource<Int, UserUiModel>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, UserUiModel> {
        return try {
            val currentPage = params.key ?: 1
            val response = getUsersUseCase.buildRequest(if (currentPage == 1) 0 else currentPage)
            val responseData = mutableListOf<UserUiModel>()
            responseData.addAll(mapper.fromList(response))
            LoadResult.Page(
                data = responseData,
                prevKey = if (currentPage == 1) null else -1,
                nextKey = response.last().id
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, UserUiModel>): Int? {
        return null
    }
}