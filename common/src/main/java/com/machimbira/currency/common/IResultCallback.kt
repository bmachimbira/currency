package com.machimbira.currency.common

interface IResultCallback<T> {

    fun succeed(result: T)

    fun fail(messages: List<String>)

}
