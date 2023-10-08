package com.xynos.talk.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.UUID


@Entity(tableName = "chats")
data class Chat(
    @PrimaryKey
    val id: String = UUID.randomUUID().toString(),
    val user1: String,
    val user2: String,
    val photoUrl: String? = null,
)
