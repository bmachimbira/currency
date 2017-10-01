package com.machimbira.currency.persistence.repository

import com.machimbira.currency.persistence.IRepository
import com.machimbira.currency.persistence.model.PersistenceRateModel
import za.co.cporm.model.query.Select

class ExchangeRateRepository : IRepository<PersistenceRateModel> {

    override fun add(model: PersistenceRateModel) {
      //  model.save()
    }

    override fun get(id: Long): PersistenceRateModel? {
        return Select.from(PersistenceRateModel::class.java).whereEquals("id", id).first()
    }

    fun getExchangeRateByCode(code: String): List<PersistenceRateModel> {
        return Select.from(PersistenceRateModel::class.java).whereEquals("code", code).sortDesc("timestamp").queryAsList()
    }

    override fun getAll(): List<PersistenceRateModel> {
        return Select.from(PersistenceRateModel::class.java).sortDesc("timestamp").queryAsList()
    }

    override fun delete(model: PersistenceRateModel) {
      //  model.delete()
    }
}