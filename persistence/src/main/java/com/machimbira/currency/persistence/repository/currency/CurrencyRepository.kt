package com.machimbira.currency.persistence.repository.currency

import com.machimbira.currency.persistence.IRepository
import com.machimbira.currency.persistence.model.PersistenceCurrencyModel
import za.co.cporm.model.CPOrm
import za.co.cporm.model.query.Select

class CurrencyRepository: ICurrencyRepository {

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

    override fun getCurrencyByCode(code: String): PersistenceCurrencyModel {
        return Select.from(PersistenceCurrencyModel::class.java).whereEquals("code", code).first()
    }

    override fun deleteAll() {
        CPOrm.deleteAll(PersistenceCurrencyModel::class.java)
    }
}
