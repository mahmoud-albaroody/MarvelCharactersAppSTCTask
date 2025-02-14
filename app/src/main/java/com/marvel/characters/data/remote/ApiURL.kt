package com.marvel.characters.data.remote

import android.os.Build
import androidx.annotation.RequiresApi
import com.marvel.characters.utils.sharedPreferences.getKeys
import java.math.BigInteger
import java.security.MessageDigest
import java.time.Instant

object ApiURL {
    const val PUBLIC_KEY = "3cf1e9c0bec588d2e27790ab8b0808eb"
     const val PRIVATE_KEY = "9ef4ead786cd41577b0db5cff245d7d9e00c376f"
    const val BASE_URL = "http://gateway.marvel.com/v1/public/"
}

fun generateMD5Hash(timestamp: String, privateKey: String, publicKey: String): String {
    val input = "$timestamp$privateKey$publicKey"
    val md = MessageDigest.getInstance("MD5")
    val digest = md.digest(input.toByteArray())
    return digest.joinToString("") { "%02x".format(it) }
}
@RequiresApi(Build.VERSION_CODES.O)
fun generateTs(): String {
    return Instant.now().epochSecond.toString() // Current timestamp in seconds
}
