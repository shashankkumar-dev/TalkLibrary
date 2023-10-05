package com.xynos.talk.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.UUID

@Entity(tableName = "users")
data class User(
    @PrimaryKey
    val id: String = UUID.randomUUID().toString(),
    val name: String,
    var photoUrl: String? = null
    // Add other fields as necessary
)

