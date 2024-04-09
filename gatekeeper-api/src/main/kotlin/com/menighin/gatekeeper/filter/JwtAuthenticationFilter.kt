package com.menighin.gatekeeper.filter

import com.menighin.gatekeeper.config.SecurityConfig
import com.menighin.gatekeeper.service.JwtService
import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.http.HttpHeaders
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource
import org.springframework.security.web.util.matcher.AntPathRequestMatcher
import org.springframework.security.web.util.matcher.RequestMatcher
import org.springframework.security.web.util.matcher.RequestMatchers
import org.springframework.web.filter.OncePerRequestFilter


//@Component
class JwtAuthenticationFilter(
    private val jwtService: JwtService
) : OncePerRequestFilter() {

    private val requestMatcher: RequestMatcher

    init {
        val matchers = SecurityConfig.OPEN_ENDPOINTS.map { AntPathRequestMatcher(it) }
        requestMatcher = RequestMatchers.allOf(*matchers.toTypedArray())
    }

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

        val authToken = jwtService.validateToken(token.split("Bearer ")[1])
        authToken.details = WebAuthenticationDetailsSource().buildDetails(request)
        SecurityContextHolder.getContext().authentication = authToken

        filterChain.doFilter(request, response)
    }

    override fun shouldNotFilter(request: HttpServletRequest): Boolean {
        return requestMatcher.matches(request)
    }
}