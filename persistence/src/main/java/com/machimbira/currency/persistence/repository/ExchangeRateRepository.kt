package com.machimbira.currency.persistence.repository

import android.content.Context
import com.machimbira.currency.persistence.IRepository
import com.machimbira.currency.persistence.model.PersistenceRateModel
import za.co.cporm.model.CPOrm
import za.co.cporm.model.query.Select

class ExchangeRateRepository: IExchangeRateRepository {

    override fun add(model: PersistenceRateModel) {
        model.save()
    }

    override fun get(id: Long): PersistenceRateModel? {
        return Select.from(PersistenceRateModel::class.java).whereEquals("_id", id).first()
    }

    override fun getExchangeRateByCode(code: Long): PersistenceRateModel {
        return Select.from(PersistenceRateModel::class.java).whereEquals("code", code).first()
    }

    override fun getAll(): List<PersistenceRateModel> {
        return Select.from(PersistenceRateModel::class.java).sortDesc("timestamp").queryAsList()
    }

    override fun delete(model: PersistenceRateModel) {
        model.delete()
    }

    override fun deleteAll(){
        CPOrm.deleteAll(PersistenceRateModel::class.java)
    }
}