package com.example.atvpondkotlin.controller

import com.example.atvpondkotlin.dto.LoginDto
import com.example.atvpondkotlin.models.UserModel
import com.example.atvpondkotlin.repositories.UserRepositories
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import java.util.Optional

data class LoginController (
    val userRepositories: UserRepositories
){

    @PostMapping("/pedro")
    fun atLogin(@RequestBody @Valid login: LoginDto): ResponseEntity<Any> {
        val register: Optional<UserModel> = userRepositories.findById(login.id)
        if(register.isEmpty){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Login not found")
        }
        if(register.get().email===login.email&& register.get().senha===login.senha){
            return ResponseEntity.status(HttpStatus.OK).body("Login Ok")
        } else {
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("Verify your password or email")
        }
    }
}