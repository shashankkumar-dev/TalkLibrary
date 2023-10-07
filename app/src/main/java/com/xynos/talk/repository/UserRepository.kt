package com.xynos.talk.repository

import com.xynos.talk.data.User

interface UserRepository {
    fun registerUser(name: String): User
    fun updatePhoto(url: String)
    fun getUser(id: String): User
    fun getProfile(): User
    fun getAllFriends(): List<User>
    fun removeFriend(user: User)
}