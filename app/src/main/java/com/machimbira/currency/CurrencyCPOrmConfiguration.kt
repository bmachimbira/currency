package com.machimbira.currency

import com.machimbira.currency.persistence.model.PersistenceCurrencyModel
import com.machimbira.currency.persistence.model.PersistenceRateModel
import za.co.cporm.model.CPOrmConfiguration
import java.util.*

class CurrencyCPOrmConfiguration : CPOrmConfiguration {

    override fun getDatabaseName(): String {
        return BuildConfig.DATABASE_NAME
    }

    override fun getDatabaseVersion(): Int {
        return 11
    }

    override fun isQueryLoggingEnabled(): Boolean {
        return false
    }

    override fun upgradeResourceDirectory(): String? {
        return null
    }

    override fun getDataModelObjects(): List<Class<*>> {
        val domainObjects = ArrayList<Class<*>>()
        domainObjects.add(PersistenceCurrencyModel::class.java)
        domainObjects.add(PersistenceRateModel::class.java)
        return domainObjects
    }
}
