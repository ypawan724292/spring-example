package com.pavan.imageProcessing.repository

import com.pavan.imageProcessing.models.Product
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.repository.PagingAndSortingRepository

interface ProductRepository : JpaRepository<Product, Long>, PagingAndSortingRepository<Product, Long> {
    fun findByName(name: String): Product?
    fun findByPrice(price: Double): Product?
    fun findByRating(rating: Int): Product?
    fun findByDate(date: String): Product?
    fun findByImageUrl(imageUrl: String): Product?
}

