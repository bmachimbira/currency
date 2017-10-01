package com.machimbira.currency.persistence.model

import za.co.cporm.model.CPDefaultRecord
import za.co.cporm.model.annotation.Column.Column
import za.co.cporm.model.annotation.Table

@Table
class PersistenceCurrencyModel: CPDefaultRecord<PersistenceCurrencyModel>(){
        @Column(columnName = "code") val code: String? = null
        @Column(columnName = "description") val description: String? = null
        @Column(columnName = "minimum_value") val minimum: Double = 0.0
}
