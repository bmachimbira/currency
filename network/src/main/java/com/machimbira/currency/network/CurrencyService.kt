package com.machimbira.currency.network

import com.machimbira.currency.network.model.CurrencyModel
import retrofit2.Call
import retrofit2.http.GET

interface CurrencyService {
    @GET("currencies.json")
    fun getCurrencies(): Call<List<CurrencyModel>>

}