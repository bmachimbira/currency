package com.machimbira.currency.configuration

import com.machimbira.currency.BuildConfig
import com.machimbira.currency.persistence.model.PersistenceCurrencyModel
import com.machimbira.currency.persistence.model.PersistenceRateModel
import com.machimbira.currency.persistence.model.TrackedRateModel
import za.co.cporm.model.CPOrmConfiguration
import java.util.*

class CurrencyCPOrmConfiguration : CPOrmConfiguration {

    override fun getDatabaseName(): String {
        return BuildConfig.DATABASE_NAME
    }

    override fun getDatabaseVersion(): Int {
        return 1
    }

    override fun isQueryLoggingEnabled(): Boolean {
        return true
    }

    override fun upgradeResourceDirectory(): String? {
        return "CPORM_UPGRADES"
    }

    override fun getDataModelObjects(): List<Class<*>> {
        val domainObjects = ArrayList<Class<*>>()
        domainObjects.add(PersistenceCurrencyModel::class.java)
        domainObjects.add(PersistenceRateModel::class.java)
        domainObjects.add(TrackedRateModel::class.java)
        return domainObjects
    }
}
