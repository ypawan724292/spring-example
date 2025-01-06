package com.pavan.imageProcessing.controller

import com.pavan.imageProcessing.models.ApiResponse
import com.pavan.imageProcessing.models.ProductDto
import com.pavan.imageProcessing.service.ProductService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/v1/products")
class ProductController(private val productService: ProductService) {

    // Create a new product
    @PostMapping
    fun createProduct(@RequestBody product: ProductDto): ResponseEntity<ApiResponse<String>> {
        productService.createProduct(product)
        val response = ApiResponse(
            data = "Product created successfully",
            meta = mapOf("timestamp" to System.currentTimeMillis())
        )
        return ResponseEntity(response, HttpStatus.CREATED)
    }

    // Update a product
    @PutMapping("/{id}")
    fun updateProduct(@PathVariable id: Long, @RequestBody product: ProductDto): ResponseEntity<ApiResponse<String>> {
        productService.updateProduct(id, product)
        val response = ApiResponse(
            data = "Product with id $id updated successfully",
            meta = mapOf("timestamp" to System.currentTimeMillis())
        )
        return ResponseEntity(response, HttpStatus.NO_CONTENT)
    }

    // Delete a product
    @DeleteMapping("/{id}")
    fun deleteProduct(@PathVariable id: Long): ResponseEntity<ApiResponse<String>> {
        productService.deleteProduct(id)
        val response = ApiResponse(
            data = "Product with id $id deleted successfully", // No body needed for delete operation
            meta = mapOf("timestamp" to System.currentTimeMillis())
        )
        return ResponseEntity(response, HttpStatus.NO_CONTENT)
    }


    @DeleteMapping
    fun deleteAll(): ResponseEntity<ApiResponse<String>> {
        productService.deleteAll()
        val response = ApiResponse(
            data = " Deleted successfully", // No body needed for delete operation
            meta = mapOf("timestamp" to System.currentTimeMillis())
        )
        return ResponseEntity(response, HttpStatus.NO_CONTENT)
    }

    // Get all products
    @GetMapping
    fun getAllProducts(): ResponseEntity<ApiResponse<List<ProductDto>>> {
        val list = productService.getAllProducts()
        val response = ApiResponse(
            data = list,
            meta = mapOf("timestamp" to System.currentTimeMillis(), "total" to list.size)
        )
        return ResponseEntity.ok(response)
    }

    // Get a product by id
    @GetMapping("/{id}")
    fun getProductById(@PathVariable id: Long): ResponseEntity<ApiResponse<ProductDto>> {
        val product = productService.getProductById(id)
        val response = ApiResponse(
            data = product,
            meta = mapOf("timestamp" to System.currentTimeMillis())
        )
        return ResponseEntity.ok(response)
    }


}
