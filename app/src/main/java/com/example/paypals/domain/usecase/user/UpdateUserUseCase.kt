package com.example.paypals.domain.usecase.user

import com.example.paypals.domain.model.User
import com.example.paypals.domain.repository.UserRepository

class UpdateUserUseCase(private val repository: UserRepository) {
     suspend operator fun invoke(user: User) {
          repository.updateUser(user)
     }
}