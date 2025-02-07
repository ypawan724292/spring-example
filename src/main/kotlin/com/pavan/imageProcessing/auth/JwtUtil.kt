package com.pavan.imageProcessing.auth

import com.pavan.imageProcessing.exception.AuthenticationExceptionHandler
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import org.springframework.stereotype.Component
import java.util.*


@Component
class JwtUtil {

    private val secretKey = "123456789"

    fun generateToken(username: String, isAccessToken: Boolean): String {
        return runCatching {
            val claims: Map<String, Any> = HashMap()
            val expirationTime = if (isAccessToken) ACCESS_TOKEN_VALIDITY
            else REFRESH_TOKEN_VALIDITY
            Jwts.builder().setClaims(claims).setSubject(username).setIssuedAt(Date(System.currentTimeMillis()))
                .setExpiration(Date(System.currentTimeMillis() + expirationTime))
                .signWith(SignatureAlgorithm.HS256, secretKey).compact()
        }.getOrElse {
            throw AuthenticationExceptionHandler("Token generation failed ${it.message}")
        }
    }


    fun validateToken(token: String): Boolean {
        return runCatching {
            Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token)
            true
        }.getOrElse {
            throw AuthenticationExceptionHandler("Invalid token")
        }
    }

    fun extractUsername(token: String): String {
        return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).body.subject
    }


    companion object {
        private const val ACCESS_TOKEN_VALIDITY = 1000 * 60 * 15
        private const val REFRESH_TOKEN_VALIDITY = 1000 * 60 * 60 * 24 * 7
    }
}