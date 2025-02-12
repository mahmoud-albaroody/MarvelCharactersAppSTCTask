package com.marvel.characters.data.remote

import java.math.BigInteger
import java.security.MessageDigest

object ApiURL {
    const val PUBLIC_KEY = "03ab1ec1139beff9f3ed72d089c4c22f"
    private const val PRIVATE_KEY = "23b2bf8eaa5eb2a8e29f9822365e395d29a1fda4"
    var HASH_KEY = generateMD5Hash(System.currentTimeMillis().toString(),PRIVATE_KEY,PUBLIC_KEY)
    const val BASE_URL = "http://gateway.marvel.com/v1/public/"
}
fun generateMD5Hash(timestamp: String, privateKey: String, publicKey: String): String {
    val input = "$timestamp$privateKey$publicKey"
    val md = MessageDigest.getInstance("MD5")
    return BigInteger(1, md.digest(input.toByteArray()))
        .toString(16)
        .padStart(32, '0') // Ensure 32-character hash
}