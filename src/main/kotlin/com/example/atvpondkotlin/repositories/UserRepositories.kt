package com.example.atvpondkotlin.repositories

import com.example.atvpondkotlin.models.UserModel
import org.springframework.data.domain.Example
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.repository.query.FluentQuery
//import org.springframework.security.core.GrantedAuthority
//import org.springframework.security.core.userdetails.UserDetails
import org.springframework.stereotype.Repository
import java.util.Optional
import java.util.UUID
import java.util.function.Function

@Repository
interface UserRepositories: JpaRepository<UserModel, UUID>   {

    fun findByEmail(email:String): Optional<UserModel>

}