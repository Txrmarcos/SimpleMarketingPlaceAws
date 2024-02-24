package com.example.atvpondkotlin.repositories

import com.example.atvpondkotlin.models.BuyProductModel
import com.example.atvpondkotlin.models.CartUserModel
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface CartUserRepositories : JpaRepository<CartUserModel, Int> {
    fun findByUserId(userId: UUID) : List<CartUserModel>



}