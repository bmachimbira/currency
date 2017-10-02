package com.machimbira.currency.persistence.model

import za.co.cporm.model.CPDefaultRecord
import za.co.cporm.model.annotation.Column.Column
import za.co.cporm.model.annotation.Table

@Table(tableName = "persistence_currency_model")
class PersistenceCurrencyModel: CPDefaultRecord<PersistenceCurrencyModel>(){
        @Column(columnName = "code") var code: String? = null
        @Column(columnName = "description") var description: String? = null
        @Column(columnName = "current_value") var currentValue: Double = 0.0
        @Column(columnName = "minimum_value") var minimumValue: Double = 0.0
}
