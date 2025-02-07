package com.pavan.imageProcessing.repository

import com.pavan.imageProcessing.models.UserInfo
import org.springframework.data.jpa.repository.JpaRepository

interface UserRepository : JpaRepository<UserInfo, String> {
    fun findByUsername(username: String): UserInfo?
}