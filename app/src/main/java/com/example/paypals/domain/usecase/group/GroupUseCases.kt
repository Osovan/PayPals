package com.example.paypals.domain.usecase.group

data class GroupUseCases(
     val getAllGroups: GetAllGroupsUseCase,
     val addGroup: AddGroupUseCase,
     val deleteGroup: DeleteGroupUseCase,
     val getGroupById: GetGroupByIdUseCase
)
