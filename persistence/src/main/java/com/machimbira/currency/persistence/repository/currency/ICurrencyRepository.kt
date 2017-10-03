package com.machimbira.currency.persistence.repository.currency

import com.machimbira.currency.persistence.IRepository
import com.machimbira.currency.persistence.model.PersistenceCurrencyModel

interface ICurrencyRepository: IRepository<PersistenceCurrencyModel> {
    fun getCurrencyByCode(code: String): PersistenceCurrencyModel
    fun deleteAll()
}