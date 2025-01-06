package com.pavan.imageProcessing.mappers

import com.pavan.imageProcessing.models.FileInfo
import com.pavan.imageProcessing.models.FileInfoDto
import com.pavan.imageProcessing.models.Product
import com.pavan.imageProcessing.models.ProductDto
import java.net.FileNameMap

fun ProductDto.toProduct(): Product {
    return Product(
        id,
        name,
        price
    )
}

fun Product.toProductDto(): ProductDto {
    return ProductDto(
        id,
        name,
        price
    )
}

fun FileInfo.toFileInfoDto(): FileInfoDto {
    return FileInfoDto(
        id,
        fileName,
        filePath,
        uploadTime
    )
}

fun FileInfoDto.toFileInfo(): FileInfo {
    return FileInfo(
        id,
        fileName,
        filePath,
        uploadTime
    )
}