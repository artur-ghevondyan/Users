package com.app.remote.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class UserDetailRemoteModel(
    @SerialName("email")
    val email: String?,
    @SerialName("name")
    val name: String?,
    @SerialName("avatar_url")
    val avatar_url: String?,
    @SerialName("company")
    val company: String?,
    @SerialName("followers")
    val followers: Int?,
    @SerialName("created_at")
    val created_at: String?
)