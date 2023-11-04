package com.example.demodatabase.dto

import com.example.demodatabase.utils.Constants.SUCCESS_CODE
import com.example.demodatabase.utils.Constants.SUCCESS_DESC

data class Response<T> (
    val statusCode: String = SUCCESS_CODE,
    val statusDesc: String = SUCCESS_DESC,
    val data: T?
)
