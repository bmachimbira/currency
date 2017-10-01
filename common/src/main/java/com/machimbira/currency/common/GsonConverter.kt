package com.machimbira.currency.common

import com.google.gson.Gson
import com.google.gson.GsonBuilder

class GsonConverter{
    companion object {
        fun mapStringToMap(body: String): Map<String, Any> {
            val gson = Gson()
            var map: Map<String, Any> = HashMap()
            map = gson.fromJson(body, map.javaClass) as Map<String, Any>
            return map
        }

        fun <T> serialize(input: T): String {
            val gson = GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ss").create()
            return gson.toJson(input)
        }

        fun <T> deserialize(input: String, outputType: Class<T>): T {
            val gson = GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ss").create()
            return gson.fromJson(input, outputType)
        }
    }
}