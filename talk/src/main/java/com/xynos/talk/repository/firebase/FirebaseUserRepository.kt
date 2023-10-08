package com.xynos.talk.repository.firebase

import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.xynos.talk.cache.UserPreferences
import com.xynos.talk.data.User
import com.xynos.talk.repository.UserRepository
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class FirebaseUserRepository @Inject constructor(
    cache: UserPreferences
): UserRepository {

    private val db = Firebase.firestore
    private val userCollection = db.collection("users")
    private val currentUser = cache.getCurrentUserId()

    override suspend fun registerUser(name: String): User {
        val newUser = User(id = currentUser, name = name)
        userCollection.document(newUser.id).set(newUser).await()
        return newUser
    }

    override suspend fun updatePhoto(url: String) {
        userCollection.document(currentUser).update("photoUrl", url).await()
    }

    override suspend fun getUser(id: String): User {
        val document = userCollection.document(id).get().await()
        return document.toObject(User::class.java) ?: throw NoSuchElementException("User with ID $id not found")
    }

    override suspend fun getProfile(): User {
        return getUser(currentUser)
    }

    override suspend fun getAllFriends(): List<User> {
        val querySnapshot = userCollection.get().await()
        return querySnapshot.toObjects(User::class.java)
    }

    override suspend fun removeFriend(user: User) {
        db.collection("users").document(currentUser).collection("friends").document(user.id).delete().await()
    }
}
