package com.app.appgithubusers.di

import com.app.common.Mapper
import com.app.data.mapper.UserDetailDomainMapper
import com.app.data.mapper.UserDomainMapper
import com.app.data.model.UserDataModel
import com.app.data.model.UserDetailDataModel
import com.app.domain.entity.UserDetailEntityModel
import com.app.domain.entity.UserEntityModel
import com.app.presentation.mapper.UserDetailDomainUiMapper
import com.app.presentation.mapper.UserDomainUiMapper
import com.app.presentation.model.UserDetailUiModel
import com.app.presentation.model.UserUiModel
import com.app.remote.mapper.UserDetailRemoteMapper
import com.app.remote.mapper.UserRemoteMapper
import com.app.remote.model.UserDetailRemoteModel
import com.app.remote.model.UserRemoteModel
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class MapperModule {

    @Binds
    abstract fun bindsUserRemoteMapper(mapper: UserRemoteMapper) : Mapper<UserRemoteModel, UserDataModel>

    @Binds
    abstract fun bindsUserDetailRemoteMapper(mapper: UserDetailRemoteMapper) : Mapper<UserDetailRemoteModel, UserDetailDataModel>

    @Binds
    abstract fun bindsUserDomainMapper(mapper: UserDomainMapper) : Mapper<UserDataModel, UserEntityModel>

    @Binds
    abstract fun bindsDetailUserDomainMapper(mapper: UserDetailDomainMapper) : Mapper<UserDetailDataModel, UserDetailEntityModel>

    @Binds
    abstract fun bindsUserDomainUiMapper(mapper : UserDomainUiMapper) : Mapper<UserEntityModel, UserUiModel>

    @Binds
    abstract fun bindsDetailUserDomainUiMapper(mapper : UserDetailDomainUiMapper) : Mapper<UserDetailEntityModel, UserDetailUiModel>
}