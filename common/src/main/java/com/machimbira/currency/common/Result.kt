package com.machimbira.currency.common

import java.util.ArrayList

class Result<T>(private val httpStatusCode: HttpStatusCode?, val response: T) {
    private val errors: MutableList<String>

    val isSuccess: Boolean
        get() {
            if (this.errors.size > 0) {
                return false
            }

            if (httpStatusCode != null) {
                if (httpStatusCode == HttpStatusCode.OK || httpStatusCode == HttpStatusCode.CREATED) {
                    return true
                }
            }
            return false
        }

    init {
        this.errors = ArrayList()
    }

    fun getErrors(): List<String> {
        return errors
    }

    fun addErrors(errorMessages: List<String>) {
        this.errors.addAll(errorMessages)
    }

    companion object {

        fun failedResult(): Result<*> {
            return Result(null, null)
        }
    }


}