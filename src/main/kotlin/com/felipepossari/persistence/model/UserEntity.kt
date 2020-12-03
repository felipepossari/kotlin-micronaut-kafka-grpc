package com.felipepossari.persistence.model

import javax.persistence.*

@Entity
@Table(name = "users")
data class UserEntity(
        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        val id: Long? = null,
        val name: String,
        val email: String)
