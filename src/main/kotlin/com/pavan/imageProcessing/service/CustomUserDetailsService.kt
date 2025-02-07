package com.pavan.imageProcessing.service

import com.pavan.imageProcessing.exception.AuthenticationExceptionHandler
import com.pavan.imageProcessing.models.UserInfo
import com.pavan.imageProcessing.repository.UserRepository
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Service


@Service
class CustomUserDetailsService(
    private val userRepository: UserRepository
) : UserDetailsService {
    override fun loadUserByUsername(username: String): UserDetails {
        val user = userRepository.findByUsername(username)
            ?: throw AuthenticationExceptionHandler("User not found")

        return User.withUsername(user.username)
            .password(user.password)
            .roles(*user.roles.split(",").toTypedArray())
            .build()
    }

    fun registerUser(user: UserInfo): UserInfo {
        if (userRepository.findByUsername(user.username) != null) {
            throw AuthenticationExceptionHandler("User already exists")
        }
        return userRepository.save(user)
    }

}