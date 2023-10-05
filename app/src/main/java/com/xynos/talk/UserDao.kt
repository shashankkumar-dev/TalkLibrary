package com.xynos.talk

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.xynos.talk.data.User

@Dao
interface UserDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun registerUser(user: User): Long

    @Query("UPDATE users SET photoUrl = :url WHERE id = :userId")
    fun updatePhoto(userId: String, url: String)

    @Query("SELECT * FROM users WHERE id = :userId")
    fun getUser(userId: String): User

    @Query("SELECT * FROM users LIMIT 1") // Assuming there's a single profile for the current user in the database.
    fun getProfile(): User

    @Query("SELECT * FROM users") // Modify as needed to fit the definition of "friends" in your database.
    fun getAllFriends(): List<User>
}
