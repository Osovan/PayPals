package com.example.paypals.domain.usecase.group

import com.example.paypals.domain.model.Group
import com.example.paypals.domain.repository.GroupRepository
import javax.inject.Inject

class DeleteGroupUseCase @Inject constructor(
     private val repository: GroupRepository,
) {
     suspend operator fun invoke(groupId: Int) {
          repository.deleteGroupById(groupId)
     }
}
