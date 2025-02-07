package com.pavan.imageProcessing.exception

import com.pavan.imageProcessing.models.Error
import jakarta.validation.ConstraintViolationException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler

@ControllerAdvice
class GlobalExceptionHandler {

    @ExceptionHandler(ProductNotFoundException::class)
    fun handleProductNotFoundException(ex: ProductNotFoundException): ResponseEntity<Error<String>> {
        val error = Error(
            message = ex.message ?: "Not Found",
            meta = mapOf("timestamp" to System.currentTimeMillis())
        )
        return ResponseEntity(error, HttpStatus.NOT_FOUND)
    }

    @ExceptionHandler(FileServiceException::class)
    fun handleProductNotFoundException(ex: FileServiceException): ResponseEntity<Error<String>> {
        val error = Error(
            message = ex.message ?: "Not Found",
            meta = mapOf("timestamp" to System.currentTimeMillis())
        )
        return ResponseEntity(error, HttpStatus.NOT_FOUND)
    }


    @ExceptionHandler(ConstraintViolationException::class)
    fun handleProductNotFoundException(ex: ConstraintViolationException): ResponseEntity<Error<String>> {
        val error = Error(
            message = ex.localizedMessage ?: "Not Found",
            meta = mapOf("timestamp" to System.currentTimeMillis())
        )
        return ResponseEntity(error, HttpStatus.BAD_REQUEST)
    }

    @ExceptionHandler(AuthenticationExceptionHandler::class)
    fun handleAuthenticationException(ex: AuthenticationExceptionHandler): ResponseEntity<Error<String>> {
        val error = Error(
            message = ex.localizedMessage ?: "Auth failed",
            meta = mapOf("timestamp" to System.currentTimeMillis())
        )
        return ResponseEntity(error, HttpStatus.BAD_REQUEST)
    }
}