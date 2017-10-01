package com.machimbira.currency.common

abstract class ResultCallback<T> : IResultCallback<T> {

    override fun succeed(result: T) {
        this.onSuccess(result)
        this.onAlways()
    }

    override fun fail(messages: List<String>) {
        this.onFailure(messages)
        this.onAlways()
    }

    protected fun onSuccess(result: T) {}

    protected fun onFailure(messages: List<String>) {

    }

    protected fun onAlways() {

    }

}