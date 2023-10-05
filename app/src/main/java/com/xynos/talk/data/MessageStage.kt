package com.xynos.talk.data

enum class MessageStage(val value: Int) {
    UNSENT(0),
    SENT(1),
    DELIVERED(2),
    READ(3)
}