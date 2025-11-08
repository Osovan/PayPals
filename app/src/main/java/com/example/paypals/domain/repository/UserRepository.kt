package com.example.paypals.domain.repository

import com.example.paypals.domain.model.User
import kotlinx.coroutines.flow.Flow

interface UserRepository {
     fun getAllUsers(): Flow<List<User>>
     suspend fun insertUser(user: User)
     suspend fun deleteUserById(userId: Int)
}