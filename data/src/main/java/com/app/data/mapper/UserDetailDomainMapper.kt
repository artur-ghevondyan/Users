package com.app.data.mapper

import com.app.common.Mapper
import com.app.data.model.UserDetailDataModel
import com.app.domain.entity.UserDetailEntityModel
import javax.inject.Inject

class UserDetailDomainMapper @Inject constructor() : Mapper<UserDetailDataModel, UserDetailEntityModel> {
    override fun from(i: UserDetailDataModel?): UserDetailEntityModel {
        return UserDetailEntityModel(
            email = i?.email,
            name = i?.name,
            avatar_url = i?.avatar_url,
            company = i?.company,
            followers = i?.followers,
            created_at = i?.created_at
        )
    }

    override fun to(o: UserDetailEntityModel?): UserDetailDataModel {
        return UserDetailDataModel(
            email = o?.email,
            name = o?.name,
            avatar_url = o?.avatar_url,
            company = o?.company,
            followers = o?.followers,
            created_at = o?.created_at
        )
    }
}