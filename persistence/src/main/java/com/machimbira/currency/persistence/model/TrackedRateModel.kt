package com.machimbira.currency.persistence.model

import za.co.cporm.model.CPDefaultRecord
import za.co.cporm.model.annotation.Column.Column
import za.co.cporm.model.annotation.Table

@Table(tableName = "tracked_rates")
class TrackedRateModel : CPDefaultRecord<TrackedRateModel>() {

    @Column(columnName = "base")
    var base: String? = null

    @Column(columnName = "code")
    var code: String? = null

    @Column(columnName = "rate")
    var rate: Double = 0.0

    @Column(columnName = "timestamp")
    var timestamp: Long = 0
}