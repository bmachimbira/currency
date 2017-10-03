package com.machimbira.currency.persistence.repository.trackedCurrencies

import com.machimbira.currency.persistence.model.TrackedRateModel
import za.co.cporm.model.CPOrm
import za.co.cporm.model.query.Select

class TrackedRatesRepository : ITrackedRateRepository {
    override fun add(model: TrackedRateModel) {
        model.save()
    }

    override fun get(id: Long): TrackedRateModel? {
        return Select.from(TrackedRateModel::class.java).whereEquals("_id", id).first()
    }

    override fun getAll(): List<TrackedRateModel> {
        return Select.from(TrackedRateModel::class.java).queryAsList()
    }

    override fun delete(model: TrackedRateModel) {
        model.delete()
    }

    override fun getExchangeRateByCode(code: Long): TrackedRateModel {
        return Select.from(TrackedRateModel::class.java).whereEquals("code", code).first()
    }

    override fun getAllExchangeRateByCode(code: String): List<TrackedRateModel> {
        return Select.from(TrackedRateModel::class.java).whereEquals("code", code).queryAsList()
    }

    override fun deleteAll() {
        CPOrm.deleteAll(TrackedRateModel::class.java)
    }
}