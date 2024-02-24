package com.example.atvpondkotlin.services

import com.example.atvpondkotlin.models.UserModel
import com.example.atvpondkotlin.repositories.UserRepositories
import java.util.Optional


data class AuthorizationService(
    val userRepositories: UserRepositories
){
    fun loadUser(email:String): Optional<UserModel>?{
        return userRepositories.findByEmail(email)
    }
}
