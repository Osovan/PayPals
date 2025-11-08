package com.example.paypals.di

import com.example.paypals.domain.repository.GroupRepository
import com.example.paypals.domain.repository.PaymentRepository
import com.example.paypals.domain.repository.UserRepository
import com.example.paypals.domain.usecase.group.AddGroupUseCase
import com.example.paypals.domain.usecase.group.DeleteGroupUseCase
import com.example.paypals.domain.usecase.group.GetAllGroupsUseCase
import com.example.paypals.domain.usecase.group.GetGroupByIdUseCase
import com.example.paypals.domain.usecase.group.GroupUseCases
import com.example.paypals.domain.usecase.payment.AddPaymentUseCase
import com.example.paypals.domain.usecase.payment.GetPaymentsForGroupUseCase
import com.example.paypals.domain.usecase.payment.PaymentUseCases
import com.example.paypals.domain.usecase.user.AddUserUseCase
import com.example.paypals.domain.usecase.user.DeleteUserUseCase
import com.example.paypals.domain.usecase.user.GetAllUsersUseCase
import com.example.paypals.domain.usecase.user.UserUseCases
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {

     @Provides
     @Singleton
     fun provideUserUseCases(userRepository: UserRepository): UserUseCases {
          return UserUseCases(
               getAllUsers = GetAllUsersUseCase(userRepository),
               addUser = AddUserUseCase(userRepository),
               deleteUser = DeleteUserUseCase(userRepository)
          )
     }

     @Provides
     @Singleton
     fun provideGroupUseCases(repository: GroupRepository): GroupUseCases {
          return GroupUseCases(
               getAllGroups = GetAllGroupsUseCase(repository),
               addGroup = AddGroupUseCase(repository),
               deleteGroup = DeleteGroupUseCase(repository),
               getGroupById = GetGroupByIdUseCase(repository)
          )
     }

     @Provides
     @Singleton
     fun providePaymentUseCases(repository: PaymentRepository): PaymentUseCases {
          return PaymentUseCases(
               addPayment = AddPaymentUseCase(repository),
               getPaymentsForGroup = GetPaymentsForGroupUseCase(repository)
          )
     }

}