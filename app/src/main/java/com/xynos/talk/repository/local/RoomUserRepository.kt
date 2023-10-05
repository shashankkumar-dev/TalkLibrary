package com.xynos.talk.repository.local

import com.xynos.talk.cache.UserPreferences
import com.xynos.talk.data.User
import com.xynos.talk.repository.UserRepository

class RoomUserRepository(private val userDao: UserDao, private val cache: UserPreferences) : UserRepository {

    override fun registerUser(name: String): String {
        val newUser = User(name = name)
        userDao.registerUser(newUser)
        cache.setCurrentUserId(newUser.id)
        return newUser.id
    }

    override fun updatePhoto(url: String) {
        val user = getProfile().apply {
            photoUrl = url
        }
        userDao.registerUser(user)
    }

    override fun getUser(id: String): User {
        return userDao.getUser(id)
    }

    override fun getProfile(): User {
        return getUser(cache.getCurrentUserId())
    }

    override fun getAllFriends(): List<User> {
        return userDao.getAllFriends()
    }

    override fun removeFriend(user: User) {
        userDao.removeFriend(user)
    }


}
