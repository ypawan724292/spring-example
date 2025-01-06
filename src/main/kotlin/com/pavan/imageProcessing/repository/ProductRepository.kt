package com.pavan.imageProcessing.repository

import com.pavan.imageProcessing.models.Product
import org.springframework.data.jpa.repository.JpaRepository

interface ProductRepository : JpaRepository<Product, Long>
