package com.xynos.talk.data

import androidx.room.Embedded
import androidx.room.Relation

data class ChatWithMessages(
    @Embedded val chat: Chat,
    @Relation(
        parentColumn = "id",
        entityColumn = "chatId"
    )
    val messages: List<Message>
)