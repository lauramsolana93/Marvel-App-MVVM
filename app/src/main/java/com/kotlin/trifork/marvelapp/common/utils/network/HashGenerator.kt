package com.kotlin.trifork.marvelapp.common.utils.network

import java.math.BigInteger
import java.security.MessageDigest

fun generateHash(text: String): String {
    val messageDigest = getDisgest(text)
    val md5 = BigInteger(1, messageDigest).toString(16)
    return ("0" * (32 - md5.length) + md5)
}

private fun getDisgest(text: String): ByteArray =
    MessageDigest.getInstance("MD5").digest(text.toByteArray())

private operator fun String.times(i: Int) = (1..i).fold("") { acc, _ ->
    acc + this
}