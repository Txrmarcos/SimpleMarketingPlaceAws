package com.example.atvpondkotlin.repositories

import com.example.atvpondkotlin.models.BuyProductModel
import com.example.atvpondkotlin.models.CartUserModel
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.Optional
import java.util.UUID

@Repository
interface BuyProductRepositories: JpaRepository<BuyProductModel,UUID> {
    fun findByEmail(email: String) : List<BuyProductModel>
}