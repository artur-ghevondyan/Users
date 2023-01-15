package com.app.domain.entity

data class UserDetailEntityModel(
    val email: String?,
    val name: String?,
    val avatar_url: String?,
    val company: String?,
    val followers: Int?,
    val created_at: String?
)