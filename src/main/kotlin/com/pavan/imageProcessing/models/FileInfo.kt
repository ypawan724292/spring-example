package com.pavan.imageProcessing.models

import jakarta.persistence.*
import java.time.LocalDateTime


@Entity
@Table(name = "file_metadata")
data class FileInfo(
    @Id
    @Column(nullable = false)
    val fileName: String,

    @Column(nullable = false)
    val filePath: String,

    @Column(nullable = false)
    val url: String,

    @Column(nullable = false)
    val uploadTime: LocalDateTime = LocalDateTime.now()
) {
    constructor() : this("", "", "")
}


data class FileInfoDto(
    val fileName: String,
    val filePath: String,
    val url: String,
    val uploadTime: LocalDateTime = LocalDateTime.now()
)