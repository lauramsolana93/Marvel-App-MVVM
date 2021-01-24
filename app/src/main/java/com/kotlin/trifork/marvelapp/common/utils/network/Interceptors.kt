package com.kotlin.trifork.marvelapp.common.utils.network

import com.kotlin.trifork.marvelapp.BuildConfig
import okhttp3.Interceptor

fun headersInterceptor() = Interceptor { chain ->

    chain.proceed(
        chain.request().newBuilder()
            .addHeader("Accept", "application/json")
            .addHeader("Accept-Language", "en")
            .addHeader("Content-Type", "application/json")
            .build()
    )
}

fun queryInterceptor() = Interceptor { chain ->

    val originalRequest = chain.request()
    val timeStamp = System.currentTimeMillis()
    var url = originalRequest.url.newBuilder()
        .addQueryParameter("apikey", BuildConfig.PUBLIC_KEY_MARVEL)
        .addQueryParameter(
            "hash",
            generateHash(
                timeStamp.toString() + BuildConfig.PRIVATE_KEY_MARVEL +
                        BuildConfig.PUBLIC_KEY_MARVEL
            )
        )
        .addQueryParameter("ts", "$timeStamp")
        .build()

    val request = originalRequest
        .newBuilder()
        .url(url)
        .build()

    chain.proceed(request)
}