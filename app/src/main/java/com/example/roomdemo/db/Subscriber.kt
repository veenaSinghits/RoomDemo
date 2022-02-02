package com.example.roomdemo.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "SubscriberTable")
data class Subscriber(

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "SubscriberId")
    val id : Int,

    @ColumnInfo(name = "SubscriberName")
    var name : String,

    @ColumnInfo(name = "SubscriberEmail")
    var email : String
)
