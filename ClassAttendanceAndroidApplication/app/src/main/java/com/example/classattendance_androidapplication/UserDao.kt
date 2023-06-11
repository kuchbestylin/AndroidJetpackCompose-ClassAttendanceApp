package com.example.classattendance_androidapplication

import androidx.room.*

// This is the UserDao interface, which defines the database operations for the User entity.
@Dao
interface UserDao {

    // This is the UserDao interface, which defines the database operations for the User entity.
    @Insert
    suspend fun addUser(users: List<User>)

    // This function retrieves all users from the database, ordered by ID in descending order.
    @Query("SELECT * FROM user ORDER BY id DESC")
    fun getAllUsers(): List<User>

    // This function updates an existing user in the database.
    @Update
    suspend fun updateUser(user: User)

    // This function deletes a user from the database.
    @Delete
    suspend fun deleteUser(user: User)
}