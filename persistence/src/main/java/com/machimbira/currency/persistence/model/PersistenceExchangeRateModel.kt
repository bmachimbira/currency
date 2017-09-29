package com.machimbira.currency.persistence.model

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey
import java.util.*

open class PersistenceExchangeRateModel(
        @PrimaryKey open var id: Long = 0,
        open var currencyCode: String = "",
        open var rate: Double = 0.0,
        open var timestamp: Long = Date().time) : RealmObject(){}