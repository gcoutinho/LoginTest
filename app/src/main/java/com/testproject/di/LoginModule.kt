package com.testproject.di

import com.testproject.data.remote.Api
import com.testproject.data.repository.LoginRepository
import com.testproject.data.repository.LoginRepositoryImpl
import com.testproject.domain.LoginInteractor
import com.testproject.domain.LoginInteractorImpl
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class LoginModule {

    @Binds
    abstract fun bindLoginRepository(
        loginRepositoryImpl: LoginRepositoryImpl
    ): LoginRepository

    @Binds
    abstract fun bindLoginInteractor(
        loginInteractorImpl: LoginInteractorImpl
    ): LoginInteractor

    companion object {
        @Provides
        fun provideApi(): Api {
            return Api()
        }
    }
}