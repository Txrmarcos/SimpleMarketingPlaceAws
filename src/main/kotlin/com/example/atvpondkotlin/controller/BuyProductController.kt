package com.example.atvpondkotlin.controller

import com.example.atvpondkotlin.dto.BuyProductDto
import com.example.atvpondkotlin.models.BuyProductModel
import com.example.atvpondkotlin.models.UserModel
import com.example.atvpondkotlin.repositories.BuyProductRepositories
import com.example.atvpondkotlin.repositories.UserRepositories
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.Optional
import java.util.UUID


@RestController
@CrossOrigin(origins = ["http://54.172.167.157"])
data class BuyProductController(
    val buyProductRepositories: BuyProductRepositories,
    val userProductModel: UserRepositories

){
    @PostMapping("/buy")
    fun addBuy (@RequestBody @Valid buyDto: BuyProductDto): ResponseEntity<BuyProductModel>? {
        val buy: BuyProductModel = BuyProductModel(productId = buyDto.productId, nItens = buyDto.nItens, email = buyDto.email,price=buyDto.price)

        return ResponseEntity.status(HttpStatus.CREATED).body(buyProductRepositories.save(buy))

    }

    @GetMapping("/buy")
    fun allPurchases() : ResponseEntity<Any>? {
        val allBuys : List<BuyProductModel> = buyProductRepositories.findAll()
        return ResponseEntity.status(HttpStatus.OK).body(allBuys)

    }

    @GetMapping("/buy/{id}")
    fun buy(@PathVariable(value = "id") id:String ) : ResponseEntity<Any>? {

        val buy: List<BuyProductModel> = buyProductRepositories.findByEmail(id)


        if(buy.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Buy not found")
        }

        return ResponseEntity.status(HttpStatus.OK).body(buy)
    }


    @DeleteMapping("/buy/{id}")
    fun deleteAll(@PathVariable(value = "id") id:String) : ResponseEntity<Any>{
        val buy: List<BuyProductModel> = buyProductRepositories.findByEmail(id)
        buyProductRepositories.deleteAllInBatch(buy)
        if(buy.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Buy not found")
        }
        return ResponseEntity.status(HttpStatus.OK).body("buy deleted Successfully")

    }

}

