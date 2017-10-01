package com.machimbira.currency.persistence

interface IRepository<T>{
    fun add(model: T)
    fun get(id: Long): T?
    fun getAll(): List<T>
    fun delete(model: T)
}