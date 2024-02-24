package com.example.atvpondkotlin.models

import jakarta.persistence.*
import java.math.BigDecimal
import java.util.UUID

@Entity
@Table(name = "BUY_OF_USER")
data class BuyProductModel(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    val buyId: UUID? = null,
    val productId:Int,
    val nItens:Int,
    val email:String,
    val price:BigDecimal

)
