package com.pavan.imageProcessing.mappers

import com.pavan.imageProcessing.models.FileInfo
import com.pavan.imageProcessing.models.FileInfoDto
import com.pavan.imageProcessing.models.Product
import com.pavan.imageProcessing.models.ProductDto

fun ProductDto.toProduct(imageUrl :String): Product {
    return Product(
        id,
        name,
        price,
        description,
        imageUrl,
        date,
        rating
    )
}

fun Product.toProductDto(): ProductDto {
    return ProductDto(
        id,
        name,
        price,
        description,
        imageUrl,
        date,
        rating
    )
}

fun FileInfo.toFileInfoDto(): FileInfoDto {
    return FileInfoDto(
        fileName,
        filePath,
        url,
        uploadTime
    )
}

fun FileInfoDto.toFileInfo(): FileInfo {
    return FileInfo(
        fileName,
        filePath,
        url,
        uploadTime
    )
}