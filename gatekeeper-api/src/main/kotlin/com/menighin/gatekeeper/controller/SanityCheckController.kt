package com.menighin.gatekeeper.controller

import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/sanity-check")
class SanityCheckController {

    @GetMapping
    fun sanityCheck(): String {
        return "Not crazy yet!"
    }

    @GetMapping("/protected")
    @PreAuthorize("hasRole('ADMIN')")
    fun sanityCheckProtected(): String {
        return "Not crazy yet! (protected)"
    }

}