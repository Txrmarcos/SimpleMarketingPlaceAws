package com.example.atvpondkotlin.models

import jakarta.persistence.*
import java.util.UUID


@Entity
@Table(name = "LIFE_OF_USER")
data class UserModel (
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id: UUID?=null,
    var name: String? = null,
    var email: String?= null,
    var senha: String?= null,
    var saldo: Int

)


