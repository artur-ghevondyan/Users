package com.app.remote.mapper

import com.app.common.Mapper
import com.app.data.model.UserDetailDataModel
import com.app.remote.model.UserDetailRemoteModel
import javax.inject.Inject

class UserDetailRemoteMapper @Inject constructor() :
    Mapper<UserDetailRemoteModel, UserDetailDataModel> {
    override fun from(i: UserDetailRemoteModel?): UserDetailDataModel {
        return UserDetailDataModel(
            email = i?.email,
            name = i?.name,
            avatar_url = i?.avatar_url,
            company = i?.company,
            followers = i?.followers,
            created_at = i?.created_at
        )
    }

    override fun to(o: UserDetailDataModel?): UserDetailRemoteModel {
        return UserDetailRemoteModel(
            email = o?.email,
            name = o?.name,
            avatar_url = o?.avatar_url,
            company = o?.company,
            followers = o?.followers,
            created_at = o?.created_at
        )
    }
}