package com.example.mynavigation

import androidx.room.*

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

    @SuppressWarnings(RoomWarnings.CURSOR_MISMATCH)
    @Query("SELECT * FROM users WHERE username = :username AND password = :password")
    fun getUser(username: String, password: String): User?
}