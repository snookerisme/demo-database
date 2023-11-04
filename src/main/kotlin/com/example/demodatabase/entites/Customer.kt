package com.example.demodatabase.entites

import org.springframework.data.relational.core.mapping.Table

@Table(name = "CUSTOMER")
data class Customer(
    var id: String?,
    var name: String?,
    var age: Int?
)
