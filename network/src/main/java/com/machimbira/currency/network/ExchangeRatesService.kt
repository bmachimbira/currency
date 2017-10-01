package com.machimbira.currency.network

import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ExchangeRatesService{
    @GET("latest.json")
    fun getRates(@Query("app_id") appId: String): Call<ResponseBody>

}