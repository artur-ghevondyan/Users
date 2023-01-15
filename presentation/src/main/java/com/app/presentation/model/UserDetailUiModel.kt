package com.app.presentation.model

data class UserDetailUiModel(
    val email: String?,
    val name: String?,
    val avatar_url: String?,
    val company: String?,
    val followers: Int?,
    val created_at: String?
)