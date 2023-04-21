package com.example.mynavigation

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface UserDao {
    @Query("select * from users where firstName=:firstName")
    fun getUsersByFirstName(firstName:String):List<User>
    @Insert
    fun addUsers(user:User)
    @Update
    fun updateUser(user:User)
    @Delete
    fun deleteUser(user:User)
}