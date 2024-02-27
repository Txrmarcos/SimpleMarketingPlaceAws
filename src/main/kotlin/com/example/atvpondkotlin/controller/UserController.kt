package com.example.atvpondkotlin.controller

import com.example.atvpondkotlin.dto.UserDto
import com.example.atvpondkotlin.models.UserModel
import com.example.atvpondkotlin.repositories.UserRepositories
import jakarta.validation.Valid
import org.springframework.beans.BeanUtils
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.Optional
import java.util.UUID

@RestController
@CrossOrigin(origins = ["http://54.172.167.157"])
data class UserController(

    val userRepositories: UserRepositories

)   {
    @PostMapping("/user")
    fun saveProduct(@RequestBody @Valid userDto: UserDto): ResponseEntity<UserModel>? {

        val userModel= UserModel(name = userDto.name, email = userDto.email, senha = userDto.senha, saldo = userDto.saldo)

        val savedUserModel = userRepositories.save(userModel)

        return ResponseEntity.status(HttpStatus.CREATED).body(savedUserModel)

    }

    @GetMapping("/users")
    fun getAllProduct() : ResponseEntity<List<UserModel>> {

        return ResponseEntity.status(HttpStatus.OK).body(userRepositories.findAll())

    }

    @GetMapping("/user/{id}")
    fun getProduct(@PathVariable(value="id") id:String): ResponseEntity<Any> {

        val user: Optional<UserModel> = userRepositories.findByEmail(id);

        if(user.isEmpty){

            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");

        }

        return ResponseEntity.status(HttpStatus.OK).body(user.get())

    }

    @PutMapping("/user/{id}")
    fun attUser(@PathVariable(value="id") id:String, @RequestBody @Valid userDto: UserDto) : ResponseEntity<Any>? {
        val userAtt : Optional<UserModel> = userRepositories.findByEmail(id)
        val userModel= UserModel(name = userDto.name, email = userDto.email, saldo = userDto.saldo)
        if (userAtt.isEmpty){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");

        }
        val userCopy = userAtt.get()
        BeanUtils.copyProperties(userDto, userCopy)

        return ResponseEntity.status(HttpStatus.OK).body(userRepositories.save(userCopy))

    }

    @DeleteMapping("/user/{id}")
    fun delete(@PathVariable (value="id") id:String):ResponseEntity<Any> {
        val userDelete: Optional<UserModel> = userRepositories.findByEmail(id);

        if(userDelete.isEmpty){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User Not found")

        }
        userRepositories.delete(userDelete.get())
        return ResponseEntity.status(HttpStatus.OK).body("User Deleted Successfully")

    }

}