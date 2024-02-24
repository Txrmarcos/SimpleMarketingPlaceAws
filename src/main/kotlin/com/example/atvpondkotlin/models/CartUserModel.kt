package com.example.atvpondkotlin.models

import jakarta.persistence.*
import java.util.UUID

@Entity
@Table(name = "CART_OF_USER")
data class CartUserModel(
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    var cartId : Int ? = null,
    var userId :UUID,
    var productId : Int,
    var nItens : Int,
    val price: Float
)
