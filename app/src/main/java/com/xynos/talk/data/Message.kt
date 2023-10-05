package com.xynos.talk.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.UUID

@Entity(tableName = "messages")
data class Message(
    @PrimaryKey
    val id: String = UUID.randomUUID().toString(),
    val text: String,
    val sender: String,
    val receiver: String,
    val stage: Int = 0,
    val timestamp: Long = System.currentTimeMillis(),
    val chatId: String
)