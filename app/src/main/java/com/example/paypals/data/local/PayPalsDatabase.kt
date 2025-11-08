package com.example.paypals.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.paypals.data.local.dao.GroupDao
import com.example.paypals.data.local.dao.PaymentDao
import com.example.paypals.data.local.dao.UserDao
import com.example.paypals.data.local.entity.GroupEntity
import com.example.paypals.data.local.entity.GroupUserCrossRef
import com.example.paypals.data.local.entity.PaymentEntity
import com.example.paypals.data.local.entity.UserEntity

@Database(
     entities = [
          UserEntity::class,
          GroupEntity::class,
          GroupUserCrossRef::class,
          PaymentEntity::class], version = 1,
     exportSchema = true
)
abstract class PayPalsDatabase : RoomDatabase() {
     abstract fun userDao(): UserDao
     abstract fun groupDao(): GroupDao
     abstract fun paymentDao(): PaymentDao
}

