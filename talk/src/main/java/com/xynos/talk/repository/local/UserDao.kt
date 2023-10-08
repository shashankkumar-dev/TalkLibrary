package com.xynos.talk.repository.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.xynos.talk.data.User

@Dao
interface UserDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun registerUser(user: User): Long

    @Query("SELECT * FROM users WHERE id = :userId")
    fun getUser(userId: String): User

    @Query("SELECT * FROM users")
    fun getAllFriends(): List<User>

    @Delete
    fun removeFriend(user: User)
}
