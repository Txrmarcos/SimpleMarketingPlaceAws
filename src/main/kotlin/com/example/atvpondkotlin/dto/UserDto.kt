package com.example.atvpondkotlin.dto

import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull

@JvmRecord
data class UserDto(
    @NotBlank val name: String?,
    @NotBlank val email: String?,
    @NotBlank val senha: String?,
    @NotBlank val saldo: Int = 1000
)

