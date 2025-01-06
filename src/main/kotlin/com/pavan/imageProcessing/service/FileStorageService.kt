package com.pavan.imageProcessing.service

import com.pavan.imageProcessing.exception.FileServiceException
import com.pavan.imageProcessing.models.FileInfo
import com.pavan.imageProcessing.repository.FileStorageRepository
import org.springframework.stereotype.Service
import org.springframework.web.multipart.MultipartFile
import java.nio.file.Files
import java.nio.file.Paths
import java.nio.file.StandardCopyOption

@Service
class FileStorageService(
    private val fileStorageRepository: FileStorageRepository
) {

    private val path = Paths.get("uploads").toAbsolutePath().normalize()

    init {
        runCatching {
            Files.createDirectories(path)
        }.getOrElse {
            throw FileServiceException("File dir creation failed.")
        }
    }

    fun storeFile(file: MultipartFile): String {
        val fileName = file.originalFilename ?: throw FileServiceException("File name is empty.")

        runCatching {
            if (fileName.contains("..")) {
                throw RuntimeException("Invalid path sequence in file name: $fileName")
            }

            val targetLocation = path.resolve(fileName)
            //save to dir
            Files.copy(file.inputStream, targetLocation, StandardCopyOption.REPLACE_EXISTING)

            // Save file metadata in the database
            val fileMetadata = FileInfo(
                fileName = fileName,
                filePath = targetLocation.toString()
            )
            fileStorageRepository.save(fileMetadata)
            return targetLocation.toString()
        }.getOrElse {
            throw FileServiceException("File upload failed")
        }
    }

    fun deleteFile(fileName: String) {
        runCatching {
            val fileMetadata = fileStorageRepository.findByFileName(fileName)
                ?: throw FileServiceException("File not found in database: $fileName")

            val filePath = Paths.get(fileMetadata.filePath).normalize()
            // delete from dir
            Files.deleteIfExists(filePath)

            // Remove file metadata from the database
            fileStorageRepository.delete(fileMetadata)
        }.getOrElse {
            throw FileServiceException("Could not delete file: $fileName")
        }
    }

}