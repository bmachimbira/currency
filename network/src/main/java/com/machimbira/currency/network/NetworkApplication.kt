package com.machimbira.currency.network

import android.app.Application
import android.content.Context
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class NetworkApplication : Application() {

    fun getClient(): Retrofit {
        val baseUrl = BuildConfig.BASE_URL

        return Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
    }
}