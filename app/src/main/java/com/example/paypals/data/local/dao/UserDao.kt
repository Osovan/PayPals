package com.example.paypals.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.paypals.data.local.entity.UserEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao {

     @Query("SELECT * FROM users")
     fun getAllUsers(): Flow<List<UserEntity>>

     @Insert(onConflict = OnConflictStrategy.REPLACE)
     suspend fun insertUser(user: UserEntity)

     @Query("DELETE FROM users WHERE id = :userId")
     suspend fun deleteUserById(userId: Int)

     @Update
     suspend fun updateUser(user: UserEntity)
}