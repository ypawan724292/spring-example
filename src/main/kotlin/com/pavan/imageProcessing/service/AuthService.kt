package com.pavan.imageProcessing.service

import com.pavan.imageProcessing.auth.JwtUtil
import com.pavan.imageProcessing.exception.AuthenticationExceptionHandler
import com.pavan.imageProcessing.models.AuthenticationRequest
import com.pavan.imageProcessing.models.AuthenticationResponse
import com.pavan.imageProcessing.models.UserInfo
import com.pavan.imageProcessing.models.UserRegistrationRequest
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service


@Service
class AuthService(
    private val authenticationManager: AuthenticationManager,
    private val jwtUtil: JwtUtil,
    private val userDetailsService: CustomUserDetailsService,
    private val passwordEncoder: PasswordEncoder

) {

    fun authenticate(authenticationRequest: AuthenticationRequest): AuthenticationResponse {
        authenticationManager.authenticate(
            UsernamePasswordAuthenticationToken(
                authenticationRequest.username,
                authenticationRequest.password
            )
        )

        val userDetails = userDetailsService.loadUserByUsername(authenticationRequest.username)
        val accessToken = jwtUtil.generateToken(userDetails.username, true)
        val refreshToken = jwtUtil.generateToken(userDetails.username, false)

        return AuthenticationResponse(accessToken, refreshToken)
    }


    fun refreshToken(refreshToken: String): AuthenticationResponse {
        if (jwtUtil.validateToken(refreshToken)) {
            val username = jwtUtil.extractUsername(refreshToken)
            val newAccessToken = jwtUtil.generateToken(username, true)
            return AuthenticationResponse(newAccessToken, refreshToken)
        } else {
            throw IllegalArgumentException("Invalid refresh token")
        }
    }

    fun registerUser(registrationRequest: UserRegistrationRequest) {
        val encodedPassword = passwordEncoder.encode(registrationRequest.password)
        val user = UserInfo(
            username = registrationRequest.username,
            password = encodedPassword,
            roles = registrationRequest.roles
        )
        userDetailsService.registerUser(user)
    }

}
