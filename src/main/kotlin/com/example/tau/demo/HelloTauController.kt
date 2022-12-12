package com.example.tau.demo

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("api/hello")
class HelloTauController {
    @GetMapping
    fun helloTau(): String = "Hello, this is a REST endpoint!"
}