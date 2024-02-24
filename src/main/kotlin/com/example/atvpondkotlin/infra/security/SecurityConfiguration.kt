//package com.example.atvpondkotlin.infra.security
//
//import org.springframework.context.annotation.Bean
//import org.springframework.context.annotation.Configuration
//import org.springframework.security.config.annotation.web.builders.HttpSecurity
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
//import org.springframework.security.config.http.SessionCreationPolicy
//import org.springframework.security.web.DefaultSecurityFilterChain
//import org.springframework.security.web.SecurityFilterChain
//
//@Configuration
//@EnableWebSecurity
//class SecurityConfiguration {
//
//    @Bean
//    fun securityFilterChain(http:HttpSecurity): DefaultSecurityFilterChain? {
//        return http.csrf { csrf -> csrf.disable() }.sessionManagement { session ->
//            session.sessionCreationPolicy(
//                SessionCreationPolicy.STATELESS
//            )
//        }.build()
//    }
//
//}