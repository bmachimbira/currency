package com.machimbira.currency.persistence.repository

import com.machimbira.currency.persistence.IRepository
import com.machimbira.currency.persistence.model.PersistenceCurrencyModel
import za.co.cporm.model.query.Select

class CurrencyRepository: IRepository<PersistenceCurrencyModel> {

    override fun add(model: PersistenceCurrencyModel) {
        model.save()
    }

    override fun get(id: Long): PersistenceCurrencyModel? {
        return Select.from(PersistenceCurrencyModel::class.java).whereEquals("id", id).first()
    }

    override fun getAll(): List<PersistenceCurrencyModel> {
        return Select.from(PersistenceCurrencyModel::class.java).queryAsList()
    }

    override fun delete(model: PersistenceCurrencyModel) {
        model.delete()
    }
}
