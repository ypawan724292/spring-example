package com.pavan.imageProcessing.repository

import com.pavan.imageProcessing.models.FileInfo
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface FileStorageRepository : JpaRepository<FileInfo, Long> {
    fun findByFileName(fileName: String): FileInfo?
}
