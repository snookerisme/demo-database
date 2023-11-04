package com.example.demodatabase.repositories

import com.example.demodatabase.entites.Customer
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.reactive.awaitFirstOrNull
import org.slf4j.LoggerFactory
import org.springframework.data.r2dbc.core.*
import org.springframework.data.relational.core.query.Criteria
import org.springframework.data.relational.core.query.Query
import org.springframework.data.relational.core.query.Update
import org.springframework.stereotype.Repository

@Repository
class CustomerRepository(
    private val r2dbcEntityTemplate: R2dbcEntityTemplate
) {

    private companion object {
        private val log = LoggerFactory.getLogger(CustomerRepository.javaClass)
    }

    internal suspend fun saveCustomer(id:String, name:String, age:Int) =
        r2dbcEntityTemplate.runCatching {
            insert(Customer::class.java).usingAndAwait(Customer(id,name,age))
        }.onFailure {
            log.info("Save customer is failure!")
        }.getOrThrow()

    internal suspend fun getCustomer(id:String) =
        r2dbcEntityTemplate.runCatching {
            select(
                Query.query(Criteria.where(Customer::id.name).`is`(id)),Customer::class.java
            ).awaitFirstOrNull()
        }.onFailure {
            log.info("Get customer is failure!")
        }.getOrThrow()

    internal suspend fun updateCustomer(id:String,name:String, age:Int) =
        r2dbcEntityTemplate.runCatching {
            update(Customer::class.java)
                .matching(
                    Query.query(Criteria.where(Customer::id.name).`is`(id))
                ).applyAndAwait(
                    Update.update(Customer::name.name,name)
                        .set(Customer::age.name,age)
                )
        }.onFailure {
            log.info("Update customer is failure!")
        }.getOrThrow()

    internal suspend fun deleteCustomer(id:String) =
        r2dbcEntityTemplate.runCatching {
            delete(Customer::class.java)
                .matching(
                    Query.query(
                        Criteria.where(Customer::id.name).`is`(id)
                    )
                ).allAndAwait()
        }.onFailure {
            log.info("Delete customer is failure!")
        }.getOrThrow()

    internal suspend fun getAllCustomer() =
        r2dbcEntityTemplate.runCatching {
            select(
                Customer::class.java
            ).flow().toList()
        }.onFailure {
            log.info("Get all customer is failure!")
        }.getOrThrow()
}