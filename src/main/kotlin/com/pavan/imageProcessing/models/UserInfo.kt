package com.pavan.imageProcessing.models

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id

@Entity
data class UserInfo(
    @Id
    val username: String,
    val password: String,
    val roles: String // Comma-separated roles
) {
    constructor() : this("", "", "")
}
