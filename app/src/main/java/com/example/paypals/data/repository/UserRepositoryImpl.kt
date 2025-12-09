package com.example.paypals.data.repository

import com.example.paypals.data.local.dao.UserDao
import com.example.paypals.data.mapper.toDomain
import com.example.paypals.data.mapper.toEntity
import com.example.paypals.domain.model.User
import com.example.paypals.domain.repository.UserRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class UserRepositoryImpl @Inject constructor(
     private val userDao: UserDao,
) : UserRepository {

     override fun getAllUsers(): Flow<List<User>> {
          return userDao.getAllUsers().map { list -> list.map { it.toDomain() } }
     }

     override suspend fun insertUser(user: User) {
          userDao.insertUser(user.toEntity())
     }

     override suspend fun deleteUserById(userId: Int) {
          userDao.deleteUserById(userId)
     }

     override suspend fun updateUser(user: User) {
          userDao.updateUser(user.toEntity())
     }
}