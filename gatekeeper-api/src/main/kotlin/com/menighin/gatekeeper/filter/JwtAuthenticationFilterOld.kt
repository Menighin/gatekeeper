package com.menighin.gatekeeper.filter

import com.menighin.gatekeeper.service.JwtService
import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.http.HttpHeaders
import org.springframework.web.filter.OncePerRequestFilter

class JwtAuthenticationFilterOld(
    private val jwtService: JwtService,
//    private val userDetailsService: UserDetailsService
) : OncePerRequestFilter() {


    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {
        val token = request.getHeader(HttpHeaders.AUTHORIZATION)

        if (token == null || !token.startsWith("Bearer ")) {
            filterChain.doFilter(request, response)
            return
        }


        jwtService.validateToken(token.split("Bearer ")[1])
        filterChain.doFilter(request, response)
    }

}