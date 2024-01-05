package com.example.translator_kmm.core.domain.util


// Instead of kotlin result class, to make it compatible with swift
sealed class Resource<T>(val data: T?, val throwable: Throwable? = null) {
    class Success<T>(data: T): Resource<T>(data)
    class Error<T>(throwable: Throwable): Resource<T>(null, throwable)
}