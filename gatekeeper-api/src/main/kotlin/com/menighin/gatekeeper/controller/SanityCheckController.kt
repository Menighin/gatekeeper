package com.menighin.gatekeeper.controller

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/sanity-check")
class SanityCheckController {

    @GetMapping
    fun sanityCheck(): String {
        throw RuntimeException("EXCEPTION UHUU")
        return "Not crazy yet!"
    }

}