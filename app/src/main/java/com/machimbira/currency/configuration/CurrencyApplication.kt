package com.machimbira.currency.configuration

import android.app.Application
import android.content.Context
import android.content.res.Configuration
import com.machimbira.currency.BuildConfig
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import za.co.cporm.model.CPOrm
import java.util.*


class CurrencyApplication : Application(){
    companion object {

        lateinit var instance: CurrencyApplication

        fun getClient(): Retrofit {
            val baseUrl = BuildConfig.BASE_URL

            return Retrofit.Builder()
                    .baseUrl(baseUrl)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
        }

        fun getContext(): Context{
            return instance
        }


        fun setLanguage(languageToLoad: String) {
            val locale = Locale(languageToLoad)
            Locale.setDefault(locale)
            val configuration = Configuration()
            configuration.setLocale(locale)
            instance.baseContext.resources.updateConfiguration(configuration, instance.baseContext.resources.displayMetrics)
        }

    }


    override fun onCreate() {
        super.onCreate()
        CPOrm.initialize(this)
        instance = this
    }

}