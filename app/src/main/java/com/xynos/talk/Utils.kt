package com.xynos.talk

import java.security.MessageDigest

object Utils {
    fun getUniqueId(id1: String, id2: String): String {
        val combined = id1 + id2
        val bytes = combined.toByteArray()
        val md = MessageDigest.getInstance("SHA-256")
        val digest = md.digest(bytes)
        return digest.joinToString("") { "%02x".format(it) }
    }
}