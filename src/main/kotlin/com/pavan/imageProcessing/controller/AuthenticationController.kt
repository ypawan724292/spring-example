package com.pavan.imageProcessing.controller

import com.pavan.imageProcessing.auth.JwtUtil
import com.pavan.imageProcessing.models.ApiResponse
import com.pavan.imageProcessing.models.AuthenticationRequest
import com.pavan.imageProcessing.models.AuthenticationResponse
import com.pavan.imageProcessing.models.UserRegistrationRequest
import com.pavan.imageProcessing.service.AuthService
import org.springframework.http.ResponseEntity
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping

@Controller
@RequestMapping("/api/v1/auth")
class AuthenticationController(
    private val authService: AuthService,
) {

    @PostMapping("/login")
    fun createAuthenticationToken(
        @RequestBody authenticationRequest: AuthenticationRequest
    ): ResponseEntity<ApiResponse<AuthenticationResponse>> {
        val response = authService.authenticate(authenticationRequest)
        return ResponseEntity.ok(ApiResponse(data = response))
    }

    @PostMapping("/refresh")
    fun refreshToken(@RequestBody refreshToken: String): ResponseEntity<ApiResponse<AuthenticationResponse>> {
        val response = authService.refreshToken(refreshToken)
        return ResponseEntity.ok(ApiResponse(data = response))
    }


    @PostMapping("/register")
    fun registerUser(@RequestBody registrationRequest: UserRegistrationRequest): ResponseEntity<ApiResponse<String>> {
        authService.registerUser(registrationRequest)
        return ResponseEntity.ok(ApiResponse(data = "User registered successfully"))
    }
}