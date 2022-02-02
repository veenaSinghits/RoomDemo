package com.example.roomdemo.db

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface SubscriberDAO {

    @Insert
    suspend fun insertSubscriber(subscriber: Subscriber)

    @Update
    suspend fun updateSubscriber(subscriber: Subscriber)

    @Delete
    suspend fun deleteSubscriber(subscriber: Subscriber)

    @Query("DELETE FROM SubscriberTable")
    suspend fun deleteAll()

    @Query("SELECT * FROM SubscriberTable")
    fun getAllSubscriber() : LiveData<List<Subscriber>>
}