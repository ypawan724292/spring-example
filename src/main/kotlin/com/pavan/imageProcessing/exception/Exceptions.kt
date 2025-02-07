package com.pavan.imageProcessing.exception

class ProductNotFoundException(message: String) : RuntimeException(message)

class FileServiceException(message: String) : RuntimeException(message)


class AuthenticationExceptionHandler(message: String) : RuntimeException(message)
