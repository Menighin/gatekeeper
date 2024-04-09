package com.menighin.gatekeeper.service

import io.jsonwebtoken.Jwts
import io.jsonwebtoken.io.Decoders
import io.jsonwebtoken.security.Keys
import org.springframework.beans.factory.annotation.Value
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource
import org.springframework.stereotype.Service
import java.util.*
import javax.crypto.SecretKey


@Service
class JwtService(
    @Value("\${security.jwt.secret-key}") private val jwtSecretKey: String,
    @Value("\${security.jwt.expiration-seconds}") private val jwtExpirationTime: Long,
    private val userDetailsService: UserDetailsService
) {

    private final var signingKey: SecretKey

    init {
        val decodedKey = Decoders.BASE64.decode(jwtSecretKey)
        signingKey = Keys.hmacShaKeyFor(decodedKey)
    }

    fun generateToken(userDetails: UserDetails, extraClaims: Map<String, Any>? = null): String {
        return Jwts
            .builder()
            .claims(extraClaims)
            .subject(userDetails.username)
            .issuedAt(Date(System.currentTimeMillis()))
            .expiration(Date(System.currentTimeMillis() + jwtExpirationTime * 1000))
            .signWith(signingKey)
            .compact()
    }

    fun validateToken(token: String): UsernamePasswordAuthenticationToken {
        val claims = Jwts
            .parser()
            .verifyWith(signingKey)
            .build()
            .parseSignedClaims(token)
            .payload

        val user = userDetailsService.loadUserByUsername(claims.subject)
        val authToken = UsernamePasswordAuthenticationToken(user, null, user.authorities)

        return authToken
    }

}