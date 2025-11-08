package com.example.paypals.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.paypals.data.local.entity.PaymentEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface PaymentDao {

     @Insert(onConflict = OnConflictStrategy.REPLACE)
     suspend fun insertPayment(paymentEntity: PaymentEntity)

     @Query("SELECT * FROM payments WHERE groupId = :groupId ORDER BY timestamp DESC")
     fun getPaymentsByGroup(groupId: Int): Flow<List<PaymentEntity>>
}