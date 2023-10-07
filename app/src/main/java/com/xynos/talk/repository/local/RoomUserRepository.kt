package com.xynos.talk.repository.local

import com.xynos.talk.cache.UserPreferences
import com.xynos.talk.data.User
import com.xynos.talk.repository.UserRepository
import javax.inject.Inject

class RoomUserRepository @Inject constructor(
    private val userDao: UserDao,
    private val cache: UserPreferences
) : UserRepository {

    override fun registerUser(name: String): User {
        val newUser = User(name = name)
        userDao.registerUser(newUser)
        cache.setCurrentUserId(newUser.id)
        cache.setCurrentUserName(name)
        return newUser
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
