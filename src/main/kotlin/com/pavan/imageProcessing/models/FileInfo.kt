package com.pavan.imageProcessing.models

import jakarta.persistence.*
import java.time.LocalDateTime


@Entity
@Table(name = "file_metadata")
data class FileInfo(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    @Column(nullable = false)
    val fileName: String,

    @Column(nullable = false)
    val filePath: String,

    @Column(nullable = false)
    val uploadTime: LocalDateTime = LocalDateTime.now()
)


data class FileInfoDto(
    val id: Long = 0,
    val fileName: String,
    val filePath: String,
    val uploadTime: LocalDateTime = LocalDateTime.now()
)