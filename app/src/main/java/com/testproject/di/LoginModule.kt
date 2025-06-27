package com.testproject.di

import com.testproject.data.repository.LoginRepository
import com.testproject.data.repository.LoginRepositoryImpl
import com.testproject.domain.LoginInteractor
import com.testproject.domain.LoginInteractorImpl
import dagger.Binds
import dagger.Module

@Module
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

    }
}