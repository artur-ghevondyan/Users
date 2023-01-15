package com.app.appgithubusers.di

import com.app.data.repository.RemoteUserSource
import com.app.data.repository.UserRepositoryImp
import com.app.domain.repository.UserRepository
import com.app.remote.source.RemoteUserImp
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun provideLocalDataSource(localDataSourceImpl: RemoteUserImp): RemoteUserSource

    @Binds
    @ViewModelScoped
    abstract fun provideUserRepository(repository: UserRepositoryImp): UserRepository
}