package com.app.presentation.viewmodel

import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.app.base.BaseViewModel
import com.app.base.UiState
import com.app.common.Mapper
import com.app.common.Resource
import com.app.domain.entity.UserEntityModel
import com.app.domain.usecase.GetUserRepositoryCountUseCase
import com.app.domain.usecase.GetUsersUseCase
import com.app.presentation.contract.UsersContract
import com.app.presentation.model.UserUiModel
import com.app.presentation.paging.UsersPagingSource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UsersViewModel @Inject constructor(
    private val getUsersUseCase: GetUsersUseCase,
    private val getUserRepoCountUseCase: GetUserRepositoryCountUseCase,
    private val userMapper : Mapper<UserEntityModel, UserUiModel>
) : BaseViewModel<UsersContract.Event, UiState, UsersContract.Effect>() {

    override fun createInitialState(): UsersContract.State {
        return UsersContract.State(
            usersRepoCount = UsersContract.UserRepoCountState.Idle
        )
    }

    override fun handleEvent(event: UsersContract.Event) {
        when (event) {
            is UsersContract.Event.GetUserRepoCount -> {
                getUserRepoCount(event.name)
            }
        }
    }

    private fun getUserRepoCount(name: String) {
        viewModelScope.launch {
            getUserRepoCountUseCase.buildRequest(name)
                .onStart { emit(Resource.Loading) }
                .collect {
                    when (it) {
                        is Resource.Loading -> {
                            setSharedState(UsersContract.SharedState(UsersContract.UserRepoCountState.Loading))
                        }
                        is Resource.Empty -> {
                            setSharedState (UsersContract.SharedState(UsersContract.UserRepoCountState.Idle))
                        }
                        is Resource.Success -> {
                            setSharedState (UsersContract.SharedState(UsersContract.UserRepoCountState.Success(it.data)))
                        }
                        is Resource.Error -> {
                            setEffect { UsersContract.Effect.ShowError(message = it.exception.message) }
                        }
                    }
                }
        }

    }

    val users = Pager(PagingConfig(1)) {
        UsersPagingSource(getUsersUseCase, userMapper)
    }.flow.cachedIn(viewModelScope)
}