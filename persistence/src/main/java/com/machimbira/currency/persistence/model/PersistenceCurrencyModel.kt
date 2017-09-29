package com.machimbira.currency.persistence.model

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

open class PersistenceCurrencyModel(
        @PrimaryKey open var id: Long = 0,
        open var code: String = "",
        open var description:String = ""): RealmObject() {}