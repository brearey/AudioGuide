package ru.oktemsec.audioguide

import com.google.gson.Gson
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody.Companion.toRequestBody
import okhttp3.logging.HttpLoggingInterceptor
import java.lang.IllegalStateException

data class SignInRequestBody(
    val email: String,
    val password: String,
)

data class SignInResponseBody(
    val token: String
)

fun main() {
    val contentType = "application/json; charset=utf-8".toMediaType()

    val loggingInterceptor = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)

    val gson = Gson()

    val client = OkHttpClient.Builder()
        .addInterceptor(loggingInterceptor)
        .build()

    val requestBody = SignInRequestBody(
        email = "admin@google.com",
        password = "123"
    )

    val requestBodyString = gson.toJson(requestBody)
    val okHtttpRequestBody = requestBodyString.toRequestBody(contentType)

    val request  =Request.Builder()
        .post(okHtttpRequestBody)
        .url("http://127.0.0.1:12345/sign-in")
        .build()

    val call = client.newCall(request)

    val response = call.execute()

    if (response.isSuccessful) {
        val responseBodyString = response.body!!.string()
        val signInResponseBody = gson.fromJson(
            responseBodyString,
            SignInResponseBody::class.java
        )
        println("TOKEN: ${signInResponseBody.token}")
    } else {
        throw IllegalStateException("Oops")
    }
}