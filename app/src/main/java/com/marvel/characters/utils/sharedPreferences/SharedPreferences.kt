package com.marvel.characters.utils.sharedPreferences

import android.content.Context
import android.content.SharedPreferences


fun saveKeys(context: Context, hash: String, ts: String) {
    val sharedPref: SharedPreferences = context.getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)
    with(sharedPref.edit()) {
        putString("hash_key", hash)
        putString("ts_key", ts)
        apply()
    }
}

fun getKeys(context: Context): Pair<String?, String?> {
    val sharedPref: SharedPreferences = context.getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)
    val privateKey = sharedPref.getString("hash_key", null)
    val publicKey = sharedPref.getString("ts_key", null)
    return Pair(privateKey, publicKey)
}