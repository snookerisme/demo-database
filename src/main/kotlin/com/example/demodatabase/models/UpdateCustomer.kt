package com.example.demodatabase.models

data class UpdateCustomerRequest(
    val id: String,
    val name: String,
    val age: Int
)

data class UpdateCustomerResponse(
    val id: String,
)