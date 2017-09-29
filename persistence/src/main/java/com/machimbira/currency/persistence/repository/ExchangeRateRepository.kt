package com.machimbira.currency.persistence.repository

import com.machimbira.currency.persistence.IRepository
import com.machimbira.currency.persistence.model.PersistenceExchangeRateModel
import io.realm.Realm
import java.util.*

class ExchangeRateRepository(var realm: Realm) : IRepository<PersistenceExchangeRateModel> {

    override fun add(model: PersistenceExchangeRateModel) {
        realm.executeTransaction {
            val rate = realm.createObject(PersistenceExchangeRateModel::class.java, Date().time)
            rate.currencyCode = model.currencyCode
            rate.rate = model.rate
            rate.timestamp = model.timestamp
        }
    }

    fun getExchangeRateByCode(code: String): List<PersistenceExchangeRateModel> {
        val result = realm.where(PersistenceExchangeRateModel::class.java).equalTo("currencyCode", code).findAll()
        return result
    }

    override fun get(id: Long): PersistenceExchangeRateModel? {
        val result = realm.where(PersistenceExchangeRateModel::class.java).equalTo("id", id).findFirst()
        return result
    }

    override fun getAll(): List<PersistenceExchangeRateModel> {
        val result = realm.where(PersistenceExchangeRateModel::class.java).findAll()
        return result
    }

    override fun delete() {
        realm.executeTransaction {
            realm.delete(PersistenceExchangeRateModel::class.java)
        }
    }
}