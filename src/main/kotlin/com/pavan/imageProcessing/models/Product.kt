package com.pavan.imageProcessing.models

import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.validation.constraints.NotBlank


@Entity
data class Product(
    @Id
    val id: Long,
    @field:NotBlank(message = "Product name cannot be null")
    val name: String,
    val price: Double,
    val description: String,
    val imageUrl: String,
    val date: String,
    val rating: Int
) {
    constructor() : this(0, "", 0.0, "", "", "", 0)
}


data class ProductDto(
    val id: Long,
    val name: String,
    val price: Double,
    val description: String,
    val imageUrl: String,
    val date: String,
    val rating: Int
)


