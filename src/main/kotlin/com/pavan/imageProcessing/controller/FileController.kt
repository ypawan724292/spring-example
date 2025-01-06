package com.pavan.imageProcessing.controller

import com.pavan.imageProcessing.models.ApiResponse
import com.pavan.imageProcessing.service.FileStorageService
import org.springframework.core.io.Resource
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.multipart.MultipartFile

@RestController
@RequestMapping("api/v1/file")
class FileController(
    private val fileStorageService: FileStorageService
) {

    @PostMapping("/upload")
    fun uploadFile(@RequestParam("file") file: MultipartFile): ResponseEntity<ApiResponse<String>> {
        val filename = fileStorageService.storeFile(file)
        val response = ApiResponse(
            data = "File uploaded successfully at path $filename",
            meta = mapOf("timestamp" to System.currentTimeMillis())
        )
        return ResponseEntity(response, HttpStatus.CREATED)
    }

    @GetMapping("/{filename}")
    fun getFile(@PathVariable fileName: String): ResponseEntity<Resource> {
        val resource  = fileStorageService.
    }
}