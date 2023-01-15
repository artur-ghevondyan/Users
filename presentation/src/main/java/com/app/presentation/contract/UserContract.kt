package com.app.presentation.contract

import com.app.base.UiEffect
import com.app.base.UiEvent
import com.app.base.UiState
import com.app.presentation.model.UserDetailUiModel

class UserContract {

    sealed class Event : UiEvent {
        data class OnFetchUser(val name: String) : Event()
    }

    data class State(
        val userState: UserState
    ) : UiState

    sealed class UserState {
        object Idle : UserState()
        object Loading : UserState()
        data class Success(val user: UserDetailUiModel) : UserState()
    }

    sealed class Effect : UiEffect {
        data class ShowError(val message: String?) : Effect()
    }
}