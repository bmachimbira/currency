package com.machimbira.currency.persistence.repository

import com.machimbira.currency.persistence.IRepository
import com.machimbira.currency.persistence.model.PersistenceCurrencyModel
import io.realm.Realm
import java.util.*

class CurrencyRepository(var realm: Realm): IRepository<PersistenceCurrencyModel>{

    override fun add(model: PersistenceCurrencyModel) {
        realm.executeTransaction {
            val currency = realm.createObject(PersistenceCurrencyModel::class.java, Date().time)
            currency.code = model.code
            currency.description = model.description
        }
    }

    override fun get(id: Long): PersistenceCurrencyModel? {
        val result = realm.where(PersistenceCurrencyModel::class.java).equalTo("id", id).findFirst()
        return result
    }

    override fun getAll(): List<PersistenceCurrencyModel> {
        val result = realm.where(PersistenceCurrencyModel::class.java).findAll()
        return result
    }
    
    override fun delete() {
        realm.executeTransaction {
            realm.delete(PersistenceCurrencyModel::class.java)
        }
    }
}