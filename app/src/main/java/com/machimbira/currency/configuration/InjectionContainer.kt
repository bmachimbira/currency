package com.machimbira.currency.configuration

import android.content.Context
import com.ericlouw.jinjectsu.jinjectsu.Jinjectsu
import com.machimbira.currency.api.currency.CurrencyApi
import com.machimbira.currency.api.currency.ICurrencyApi
import com.machimbira.currency.api.exchangeRate.ExchangeRateApi
import com.machimbira.currency.api.exchangeRate.IExchangeRateApi
import com.machimbira.currency.api.trackedExchangeRates.ITrackedExchangeRatesApi
import com.machimbira.currency.api.trackedExchangeRates.TrackedExchangeRatesApi
import com.machimbira.currency.features.addCurrencyScreen.AddCurrencyPresenter
import com.machimbira.currency.features.addCurrencyScreen.IAddCurrencyContract
import com.machimbira.currency.features.convertCurrencyScreen.ConvertCurrencyPresenter
import com.machimbira.currency.features.convertCurrencyScreen.IConvertCurrencyContract
import com.machimbira.currency.features.currencyDetailScreen.CurrencyDetailPresenter
import com.machimbira.currency.features.startUpScreen.CurrencyPresenter
import com.machimbira.currency.features.startUpScreen.ICurrencyContract
import com.machimbira.currency.mapper.CurrencyPersistedMapper
import com.machimbira.currency.mapper.ExchangeRatePersistenceMapper
import com.machimbira.currency.mapper.TrackedExchangeRateMapper
import com.machimbira.currency.network.CurrencyService
import com.machimbira.currency.network.ExchangeRatesService
import com.machimbira.currency.network.mapper.CurrencyMapper
import com.machimbira.currency.network.mapper.ExchangeRateMapper
import com.machimbira.currency.network.resources.currency.CurrencyResources
import com.machimbira.currency.network.resources.currency.ICurrencyResources
import com.machimbira.currency.network.resources.exchangeRate.ExchangeRateResources
import com.machimbira.currency.network.resources.exchangeRate.IExchangeRateResources
import com.machimbira.currency.persistence.preferences.PreferencesManager
import com.machimbira.currency.persistence.repository.currency.CurrencyRepository
import com.machimbira.currency.persistence.repository.currency.ICurrencyRepository
import com.machimbira.currency.persistence.repository.exchangeRates.ExchangeRateRepository
import com.machimbira.currency.persistence.repository.exchangeRates.IExchangeRateRepository
import com.machimbira.currency.persistence.repository.trackedCurrencies.ITrackedRateRepository
import com.machimbira.currency.persistence.repository.trackedCurrencies.TrackedRatesRepository
import com.machimbira.currency.service.CurrencyRatesService
import com.machimbira.currency.service.TrackedCurrencyRatesPresenter


object InjectionContainer {

    private var instance: Jinjectsu? = null

    fun getInstalledInstance(context: Context): Jinjectsu {
        if (instance == null) {
            instance = Jinjectsu()
            configure(context = context, container = instance!!)
        }
        return instance!!
    }

    private fun configure(context: Context, container: Jinjectsu) {
        try {
            container

                    //Persistence repositories
                    .bind(IExchangeRateRepository::class.java).lifestyleTransient(ExchangeRateRepository::class.java)
                    .bind(ITrackedRateRepository::class.java).lifestyleTransient(TrackedRatesRepository::class.java)
                    .bind(ICurrencyRepository::class.java).lifestyleTransient(CurrencyRepository::class.java)

                    //Persistence layer mappers
                    .bind(TrackedExchangeRateMapper::class.java).lifestyleTransient(TrackedExchangeRateMapper::class.java)
                    .bind(ExchangeRatePersistenceMapper::class.java).lifestyleTransient(ExchangeRatePersistenceMapper::class.java)
                    .bind(CurrencyPersistedMapper::class.java).lifestyleTransient(CurrencyPersistedMapper::class.java)

                    //Presenters
                    .bind(IAddCurrencyContract.UserActions::class.java).lifeStyleScoped(AddCurrencyPresenter::class.java)
                    .bind(IConvertCurrencyContract.UserActions::class.java).lifeStyleScoped(ConvertCurrencyPresenter::class.java)
                    .bind(ICurrencyContract.UserActions::class.java).lifeStyleScoped(CurrencyPresenter::class.java)
                    .bind(CurrencyDetailPresenter::class.java).lifeStyleScoped(CurrencyDetailPresenter::class.java)
                    .bind(TrackedCurrencyRatesPresenter::class.java).lifeStyleScoped(TrackedCurrencyRatesPresenter::class.java)

                    //Shared Preferences
                    .bind(PreferencesManager::class.java).lifestyleSingleton<Any> { PreferencesManager(context = context) }

                    //Apis
                    .bind(ITrackedExchangeRatesApi::class.java).lifestyleTransient(TrackedExchangeRatesApi::class.java)
                    .bind(IExchangeRateApi::class.java).lifestyleTransient(ExchangeRateApi::class.java)
                    .bind(ICurrencyApi::class.java).lifestyleTransient(CurrencyApi::class.java)

                    //NetworkResources
                    .bind(ICurrencyResources::class.java).lifestyleTransient(CurrencyResources::class.java)
                    .bind(IExchangeRateResources::class.java).lifestyleTransient(ExchangeRateResources::class.java)

                    //NetworkServices
                    .bind(ExchangeRatesService::class.java).lifestyleSingleton<Any> { CurrencyApplication.getClient().create(ExchangeRatesService::class.java)}
                    .bind(CurrencyService::class.java).lifestyleSingleton<Any> { CurrencyApplication.getClient().create(CurrencyService::class.java)}

                    //Network Mappers
                    .bind(ExchangeRateMapper::class.java).lifestyleTransient(ExchangeRateMapper::class.java)
                    .bind(CurrencyMapper::class.java).lifestyleTransient(CurrencyMapper::class.java)

                    //Service
                    .bind(CurrencyRatesService::class.java).lifestyleTransient(CurrencyRatesService::class.java)

                    .bind(Context::class.java).providedByScope()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}