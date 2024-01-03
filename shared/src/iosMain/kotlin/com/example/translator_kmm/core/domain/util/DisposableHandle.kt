package com.example.translator_kmm.core.domain.util


// The purpose of this interface is to provide a way to cancel a coroutine in iOS
fun interface DisposableHandle : kotlinx.coroutines.DisposableHandle

/*
It is a SAM constructor, which is a Kotlin feature that allows you to create an
instance of an interface with a single abstract method (SAM) using a lambda expression.
It is the same as:
fun DisposableHandle(block: () -> Unit): kotlinx.coroutines.DisposableHandle {
   return object: kotlinx.coroutines.DisposableHandle {
   override fun dispose() {
      block() }
   }
}
 */

