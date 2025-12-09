package com.example.paypals.domain.usecase.user

data class UserUseCases(
     val addUser: AddUserUseCase,
     val deleteUser: DeleteUserUseCase,
     val getAllUsers: GetAllUsersUseCase,
     val updateUser: UpdateUserUseCase
)