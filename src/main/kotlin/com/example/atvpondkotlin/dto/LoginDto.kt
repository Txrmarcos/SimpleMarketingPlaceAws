package com.example.atvpondkotlin.dto


import jakarta.persistence.Entity
import jakarta.validation.constraints.NotBlank
import java.util.UUID

@JvmRecord
data class LoginDto(
    @NotBlank val id: UUID,
    @NotBlank val email: String,
    @NotBlank val senha: String
)

