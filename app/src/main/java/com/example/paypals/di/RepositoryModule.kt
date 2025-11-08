package com.example.paypals.di

import com.example.paypals.data.repository.GroupRepositoryImpl
import com.example.paypals.data.repository.PaymentRepositoryImpl
import com.example.paypals.data.repository.UserRepositoryImpl
import com.example.paypals.domain.repository.GroupRepository
import com.example.paypals.domain.repository.PaymentRepository
import com.example.paypals.domain.repository.UserRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

     @Binds
     @Singleton
     abstract fun bindUserRepository(
          userRepositoryImpl: UserRepositoryImpl,
     ): UserRepository

     @Binds
     @Singleton
     abstract fun bindGroupRepository(
          groupRepositoryImpl: GroupRepositoryImpl,
     ): GroupRepository

     @Binds
     @Singleton
     abstract  fun bindPaymentRepository(
          paymentRepositoryImpl: PaymentRepositoryImpl
     ): PaymentRepository
}