package com.pavan.imageProcessing.service

import com.pavan.imageProcessing.exception.ProductNotFoundException
import com.pavan.imageProcessing.mappers.toProduct
import com.pavan.imageProcessing.mappers.toProductDto
import com.pavan.imageProcessing.models.ProductDto
import com.pavan.imageProcessing.repository.ProductRepository
import org.springframework.stereotype.Service

@Service
class ProductService(private val productRepository: ProductRepository) {

    fun createProduct(productDto: ProductDto) {
        if (productRepository.existsById(productDto.id).not()) {
            productRepository.save(productDto.toProduct())
        } else {
            throw ProductNotFoundException("Product with id ${productDto.id} already exist")
        }
    }

    fun updateProduct(id: Long, updatedProduct: ProductDto) {
        if (productRepository.existsById(id)) {
            val product = updatedProduct.copy(id = id)
            productRepository.save(product.toProduct())
        } else {
            throw ProductNotFoundException("Product with id $id not found")
        }
    }

    fun deleteProduct(id: Long) {
        if (productRepository.existsById(id)) {
            productRepository.deleteById(id)
        } else {
            throw ProductNotFoundException("Product with id $id not found")
        }
    }

    fun getAllProducts(): List<ProductDto> {
        val list = productRepository.findAll().map {
            it.toProductDto()
        }
        return list.ifEmpty { throw ProductNotFoundException("Products not found") }
    }

    fun getProductById(id: Long): ProductDto? {
        val product = productRepository.findById(id).orElse(null)
            ?: throw ProductNotFoundException("Product with id $id not found")
        return product.toProductDto()
    }

    fun deleteAll() {
        productRepository.deleteAll()
    }

}
