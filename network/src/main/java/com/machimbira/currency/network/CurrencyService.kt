package com.machimbira.currency.network

import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.GET

interface CurrencyService {
    @GET("currencies.json")
    fun getCurrencies(): Call<ResponseBody>

}