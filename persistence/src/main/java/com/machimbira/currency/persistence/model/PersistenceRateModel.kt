package com.machimbira.currency.persistence.model

import za.co.cporm.model.CPDefaultRecord
import za.co.cporm.model.annotation.Column.Column
import za.co.cporm.model.annotation.Table


@Table
class PersistenceRateModel : CPDefaultRecord<PersistenceRateModel>() {

    @Column(columnName = "code")
    val code: String? = null

    @Column(columnName = "rate")
    val rate: Double = 0.0

    @Column(columnName = "timestamp")
    val timestamp: Long = 0
}