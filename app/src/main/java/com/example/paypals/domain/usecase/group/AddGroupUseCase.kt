package com.example.paypals.domain.usecase.group

import com.example.paypals.domain.model.Group
import com.example.paypals.domain.repository.GroupRepository
import javax.inject.Inject

class AddGroupUseCase @Inject constructor(
     private val repository: GroupRepository
) {
     suspend operator fun invoke(group: Group): Int {
          return repository.addGroup(group)
     }
}
