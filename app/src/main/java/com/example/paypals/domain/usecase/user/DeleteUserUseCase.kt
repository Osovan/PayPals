package com.example.paypals.domain.usecase.user

import com.example.paypals.domain.repository.UserRepository

class DeleteUserUseCase(private val repository: UserRepository) {
     suspend operator fun invoke(userId: Int) {
          repository.deleteUserById(userId)
     }
}