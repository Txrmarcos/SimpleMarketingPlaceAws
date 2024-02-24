package com.example.atvpondkotlin.dto

import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull
import java.util.UUID

@JvmRecord
data class CartUserDto(
    @NotBlank val userId: UUID,
    @NotNull val productId :Int,
    @NotNull  val nIntes: Int,
    @NotNull  val price: Float,
)
