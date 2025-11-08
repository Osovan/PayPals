package com.example.paypals.di

import android.app.Application
import androidx.room.Room
import com.example.paypals.data.local.PayPalsDatabase
import com.example.paypals.data.local.dao.GroupDao
import com.example.paypals.data.local.dao.PaymentDao
import com.example.paypals.data.local.dao.UserDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

     @Provides
     @Singleton
     fun provideDatabase(app: Application): PayPalsDatabase {
          return Room.databaseBuilder(
                    app,
                    PayPalsDatabase::class.java,
                    "paypals_db"
               ).fallbackToDestructiveMigration(false).build()
     }

     @Provides
     @Singleton
     fun provideUserDao(db: PayPalsDatabase): UserDao = db.userDao()

     @Provides
     @Singleton
     fun provideGroupDao(db: PayPalsDatabase): GroupDao = db.groupDao()

     @Provides
     @Singleton
     fun providePaymentDao(db: PayPalsDatabase): PaymentDao = db.paymentDao()


}