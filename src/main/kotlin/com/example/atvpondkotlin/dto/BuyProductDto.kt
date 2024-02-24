package com.example.atvpondkotlin.dto

import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull
import java.math.BigDecimal

@JvmRecord
data class BuyProductDto(
    @NotNull val productId: Int,
    @NotNull val nItens: Int,
    @NotNull val email:String,
    @NotNull val price: BigDecimal
)
