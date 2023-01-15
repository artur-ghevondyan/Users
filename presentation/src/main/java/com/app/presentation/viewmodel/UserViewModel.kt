package com.app.presentation.viewmodel

import androidx.lifecycle.viewModelScope
import com.app.base.BaseViewModel
import com.app.common.Mapper
import com.app.common.Resource
import com.app.domain.entity.UserDetailEntityModel
import com.app.domain.usecase.GetUserUseCase
import com.app.presentation.contract.UserContract
import com.app.presentation.model.UserDetailUiModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor(
    private val getUserUseCase: GetUserUseCase,
    private val userMapper: Mapper<UserDetailEntityModel, UserDetailUiModel>
) : BaseViewModel<UserContract.Event, UserContract.State, UserContract.Effect>() {
    override fun createInitialState(): UserContract.State {
        return UserContract.State(
            userState = UserContract.UserState.Idle,
        )
    }

    override fun handleEvent(event: UserContract.Event) {
        when (event) {
            is UserContract.Event.OnFetchUser -> {
                getUserData(event.name)
            }
        }
    }

    private fun getUserData(name: String) {
        viewModelScope.launch {
            getUserUseCase.buildRequest(name)
                .onStart { emit(Resource.Loading) }
                .collect {
                    when (it) {
                        is Resource.Loading -> {
                            setState { copy(userState = UserContract.UserState.Loading) }
                        }
                        is Resource.Empty -> {
                            setState { copy(userState = UserContract.UserState.Idle) }
                        }
                        is Resource.Success -> {
                            setState {
                                copy(
                                    userState = UserContract.UserState.Success(
                                        userMapper.from(
                                            it.data
                                        )
                                    )
                                )
                            }
                        }
                        is Resource.Error -> {
                            setEffect { UserContract.Effect.ShowError(message = it.exception.message) }
                        }
                    }
                }
        }
    }
}