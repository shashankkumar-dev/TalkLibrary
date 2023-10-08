package com.xynos.talk.repository

import com.xynos.talk.data.User

interface UserRepository {
    suspend fun registerUser(name: String): User
    suspend fun updatePhoto(url: String)
    suspend fun getUser(id: String): User
    suspend fun getProfile(): User
    suspend fun getAllFriends(): List<User>
    suspend fun removeFriend(user: User)
}