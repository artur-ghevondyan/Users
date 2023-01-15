package com.app.remote.api

import com.app.remote.model.UserDetailRemoteModel
import com.app.remote.model.UserRemoteModel
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @GET("users")
    suspend fun getUsers(@Query("since") since: Int, @Query("per_page") per_page: Int): List<UserRemoteModel>

    @GET("users/{userName}")
    suspend fun getUser(@Path("userName") userName: String): UserDetailRemoteModel

    @GET("users/{userName}/repos")
    suspend fun getUserRepositoryCount(@Path("userName") userName: String): List<Any?>

    @GET("users/{userName}/followers")
    suspend fun getUserFollowers(@Path("userName") userName: String): List<UserRemoteModel>
}