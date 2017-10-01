package com.machimbira.currency

import android.app.Application
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import za.co.cporm.model.CPOrm


class CurrencyApplication : Application(){

    companion object {

        fun getClient(): Retrofit {
            val baseUrl = BuildConfig.BASE_URL

            return Retrofit.Builder()
                    .baseUrl(baseUrl)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
        }
    }


    override fun onCreate() {
        super.onCreate()
        CPOrm.initialize(this)
    }

}