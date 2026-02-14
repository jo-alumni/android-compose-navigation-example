package com.example.navigationtest.api.core

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.delete
import io.ktor.client.request.get
import io.ktor.client.request.patch
import io.ktor.client.request.post
import io.ktor.client.request.put
import io.ktor.client.request.setBody
import io.ktor.client.request.url

suspend inline fun <reified T> HttpClient.get(path: String): T {
    return get { url(path) }.body<T>()
}

suspend inline fun <reified T, reified R> HttpClient.post(path: String, body: R? = null): T {
    return post {
        url(path)
        body?.let { setBody(it) }
    }.body<T>()
}

suspend inline fun <reified T, reified R> HttpClient.patch(path: String, body: R? = null): T {
    return patch {
        url(path)
        body?.let { setBody(it) }
    }.body<T>()
}

suspend inline fun <reified T, reified R> HttpClient.put(path: String, body: R? = null): T {
    return put {
        url(path)
        body?.let { setBody(it) }
    }.body<T>()
}

suspend inline fun <reified T> HttpClient.delete(path: String): T {
    return delete { url(path) }.body<T>()
}

suspend inline fun <reified T> HttpClient.post(path: String): T {
    return post<T, Unit>(path, null)
}
