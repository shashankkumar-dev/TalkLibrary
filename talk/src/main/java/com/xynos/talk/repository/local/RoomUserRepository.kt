package com.xynos.talk.repository.local

import com.xynos.talk.cache.UserPreferences
import com.xynos.talk.data.User
import com.xynos.talk.repository.UserRepository
import javax.inject.Inject

class RoomUserRepository @Inject constructor(
    private val userDao: UserDao,
    private val cache: UserPreferences
) : UserRepository {

    override suspend fun registerUser(name: String): User {
        val newUser = User(name = name)
        userDao.registerUser(newUser)
        return newUser
    }

    override suspend fun updatePhoto(url: String) {
        val user = getProfile().apply {
            photoUrl = url
        }
        userDao.registerUser(user)
    }

    override suspend fun getUser(id: String): User {
        return userDao.getUser(id)
    }

    override suspend fun getProfile(): User {
        return getUser(cache.getCurrentUserId())
    }

    override suspend fun getAllFriends(): List<User> {
        return userDao.getAllFriends()
    }

    override suspend fun removeFriend(user: User) {
        userDao.removeFriend(user)
    }

    suspend fun addFriend(user: User) {
        userDao.registerUser(user)
    }

}
