package com.app.presentation.mapper

import com.app.common.Mapper
import com.app.domain.entity.UserDetailEntityModel
import com.app.presentation.model.UserDetailUiModel
import javax.inject.Inject

class UserDetailDomainUiMapper  @Inject constructor() :
    Mapper<UserDetailEntityModel, UserDetailUiModel> {
    override fun from(i: UserDetailEntityModel?): UserDetailUiModel {
        return UserDetailUiModel(
            email = i?.email,
            name = i?.name,
            avatar_url = i?.avatar_url,
            company = i?.company,
            followers = i?.followers,
            created_at = i?.created_at
        )
    }

    override fun to(o: UserDetailUiModel?): UserDetailEntityModel {
        return UserDetailEntityModel(
            email = o?.email,
            name = o?.name,
            avatar_url = o?.avatar_url,
            company = o?.company,
            followers = o?.followers,
            created_at = o?.created_at
        )
    }
}