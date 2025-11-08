package com.example.paypals.domain.usecase.group

import com.example.paypals.domain.model.Group
import com.example.paypals.domain.repository.GroupRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetGroupByIdUseCase @Inject constructor(
     private val repository: GroupRepository
) {
     suspend operator fun invoke(groupId: Int): Flow<Group?> {
          return repository.getGroupById(groupId)
     }
}