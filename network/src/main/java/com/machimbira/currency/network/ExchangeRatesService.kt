package com.machimbira.currency.network

import com.machimbira.currency.network.model.ExchangeRateModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ExchangeRatesService{
    @GET("latest.json")
    fun getRates(@Query("app_id") appId: String): Call<ExchangeRateModel>

}