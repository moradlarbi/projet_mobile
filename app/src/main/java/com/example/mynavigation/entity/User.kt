package com.example.mynavigation.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
@Entity(tableName = "users")
data class User(
    @PrimaryKey(autoGenerate = true)
    var id: Int?=null,
    var firstName:String?,
    var lastName:String?,
    var username: String?,
    var password: String?
)
