package com.example.paypals.ui.screen.pay

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.paypals.R
import com.example.paypals.domain.model.Payment
import com.example.paypals.domain.repository.PaymentRepository
import com.example.paypals.domain.usecase.group.GroupUseCases
import com.example.paypals.ui.components.EmptyState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject


data class PaymentWithUser(
     val payment: Payment,
     val userName: String,
)

@HiltViewModel
class PaymentViewModel @Inject constructor(
     private val paymentRepository: PaymentRepository,
     private val groupUseCases: GroupUseCases,
) : ViewModel() {

     val groups = flow {
          emitAll(groupUseCases.getAllGroups()) // ← getAllGroups ahora es un suspending operator
     }.stateIn(viewModelScope, SharingStarted.Lazily, emptyList())

     init {
          viewModelScope.launch {
               groups
                    .filter { it.isNotEmpty() && _selectedGroupId.value == null }
                    .collect { list ->
                         _selectedGroupId.value = list.first().id
                    }
          }
     }

     private val _selectedGroupId = MutableStateFlow<Int?>(null)

     fun selectGroup(groupId: Int) {
          _selectedGroupId.value = groupId
     }


     val emptyState: StateFlow<EmptyState?> = groups.map { userList ->
          if (userList.isEmpty()) {
               EmptyState(
                    imageRes = R.drawable.ghostnodatapay,
                    message = "No hay grupos con los que pagar"
               )
          } else {
               null
          }
     }.stateIn(viewModelScope, SharingStarted.Lazily, null)

     // Exponer el grupo actual con sus usuarios (asumiendo que Group tiene lista de usuarios)
     val currentGroup = _selectedGroupId
          .filterNotNull()
          .flatMapLatest { groupId ->
               groupUseCases.getGroupById(groupId)
          }
          .stateIn(viewModelScope, SharingStarted.Lazily, null)

     // Historial de pagos para el grupo
     val paymentsHistory = _selectedGroupId
          .filterNotNull()
          .flatMapLatest { groupId ->
               paymentRepository.getPaymentsForGroup(groupId)
          }
          .stateIn(viewModelScope, SharingStarted.Lazily, emptyList())


     val paymentsWithUserNames = combine(currentGroup, paymentsHistory) { group, payments ->
          if (group == null) return@combine emptyList()
          val usersMap = group.members.associateBy { it.id }
          payments.map { payment ->
               val userName = usersMap[payment.userId]?.name ?: "Desconocido"
               PaymentWithUser(payment, userName)
          }
     }.stateIn(viewModelScope, SharingStarted.Lazily, emptyList())

     // Determinar siguiente usuario que debe pagar (lógica simple: el siguiente de la lista que menos ha pagado)
     val nextUserToPay = combine(currentGroup, paymentsHistory) { group, payments ->
          group?.members?.minByOrNull { member ->
               payments.count { it.userId == member.id }
          }
     }.stateIn(viewModelScope, SharingStarted.Lazily, null)

     // Función para registrar un pago
     fun registerPayment(amount: Double) {
          val groupId = _selectedGroupId.value ?: return
          val user = nextUserToPay.value ?: return

          viewModelScope.launch {
               val payment = Payment(
                    id = 0,
                    groupId = groupId,
                    userId = user.id,
                    amount = amount,
                    timestamp = System.currentTimeMillis()
               )
               paymentRepository.addPayment(payment)
          }
     }
}
