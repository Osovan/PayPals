package com.example.paypals.domain.usecase.group

import com.example.paypals.domain.model.Group
import com.example.paypals.domain.repository.GroupRepository
import kotlinx.coroutines.flow.Flow

class GetAllGroupsUseCase(private val repository: GroupRepository) {
     suspend operator fun invoke(): Flow<List<Group>> = repository.getAllGroups()
}
