package com.example.atvpondkotlin.controller

import com.example.atvpondkotlin.dto.CartUserDto
import com.example.atvpondkotlin.models.CartUserModel
import com.example.atvpondkotlin.repositories.CartUserRepositories
import jakarta.validation.Valid
import org.springframework.beans.BeanUtils
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Repository
import org.springframework.web.bind.annotation.*
import java.util.Optional
import java.util.UUID

@RestController
@CrossOrigin
data class CartUserController (
    val cartUserRepositories: CartUserRepositories
) {

    @PostMapping("/cart")
    fun addCart(@RequestBody @Valid cartUser: CartUserDto) : ResponseEntity<CartUserModel>? {
        val cartObject = CartUserModel(userId = cartUser.userId, productId = cartUser.productId, nItens = cartUser.nIntes, price = cartUser.price )

        val savedCardModel = cartUserRepositories.save(cartObject)

        return ResponseEntity.status(HttpStatus.CREATED).body(savedCardModel)
    }

    @GetMapping("/carts")
    fun allCarts() : ResponseEntity<List<CartUserModel>>? {
        val allCarts = cartUserRepositories.findAll()
        return ResponseEntity.status(HttpStatus.OK).body(allCarts)

    }

    @GetMapping("/cart/{id}")
    fun findCart(@PathVariable(value = "id") id:UUID): ResponseEntity<Any>?{
        val cart : List<CartUserModel> = cartUserRepositories.findByUserId(id)
        if(cart.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cart not found")
        }
        return ResponseEntity.status(HttpStatus.OK).body(cart)
    }

    @PutMapping("/cart/{id}")
    fun attCart(@PathVariable(value = "id") id:Int, @RequestBody @Valid cartUser: CartUserDto): ResponseEntity<Any>? {
        val cart : Optional<CartUserModel> = cartUserRepositories.findById(id)
        if(cart.isEmpty){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cart not found")
        }
        val newCart = cart.get()

        BeanUtils.copyProperties(cartUser,newCart)

        return ResponseEntity.status(HttpStatus.OK).body(cartUserRepositories.save(newCart))

    }


    @DeleteMapping("/cart/{id}")
    fun deleteAll(@PathVariable(value = "id") id:UUID) : ResponseEntity<Any>{
        val cart = cartUserRepositories.findByUserId(id)
        cartUserRepositories.deleteAllInBatch(cart)
        return ResponseEntity.status(HttpStatus.OK).body("Cart deleted Successfully")

    }



}