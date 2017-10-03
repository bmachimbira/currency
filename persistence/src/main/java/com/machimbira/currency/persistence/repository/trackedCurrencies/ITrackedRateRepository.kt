package com.machimbira.currency.persistence.repository.trackedCurrencies

import com.machimbira.currency.persistence.IRepository
import com.machimbira.currency.persistence.model.TrackedRateModel

interface ITrackedRateRepository : IRepository<TrackedRateModel>{
    fun getExchangeRateByCode(code: Long): TrackedRateModel
    fun getAllExchangeRateByCode(code: String): List<TrackedRateModel>
    fun deleteAll()
}