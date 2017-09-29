package com.machimbira.currency.common

interface IResultCallback<T> {

    fun succeed(result: com.machimbira.currency.network.model.ExchangeRateModel?)

    fun fail(messages: List<String>)

}
