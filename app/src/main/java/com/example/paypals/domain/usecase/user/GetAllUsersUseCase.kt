package com.example.paypals.domain.usecase.user

import com.example.paypals.domain.model.User
import com.example.paypals.domain.repository.UserRepository
import kotlinx.coroutines.flow.Flow

class GetAllUsersUseCase(private val repository: UserRepository) {
     operator fun invoke(): Flow<List<User>> = repository.getAllUsers()
}