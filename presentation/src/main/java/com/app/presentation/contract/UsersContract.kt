package com.app.presentation.contract

import com.app.base.UiEffect
import com.app.base.UiEvent
import com.app.base.UiState

class UsersContract {
    sealed class Event : UiEvent {
        data class GetUserRepoCount(val name : String) : Event()
    }

    data class State(
        val usersRepoCount: UserRepoCountState
    ) : UiState

    data class SharedState(
        val usersRepoCount: UserRepoCountState
    ) : UiState

    sealed class UserRepoCountState {
        object Idle : UserRepoCountState()
        object Loading : UserRepoCountState()
        data class Success(val count : Int) : UserRepoCountState()
    }

    sealed class Effect : UiEffect {
        data class ShowError(val message : String?) : Effect()
    }
}