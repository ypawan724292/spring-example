package com.pavan.imageProcessing.models

data class ApiResponse<T>(
    val data: T?,
    val meta: Map<String, Any>? = null
)

data class Error<T>(
    val message: T, val meta: Map<String, Any>? = null
)