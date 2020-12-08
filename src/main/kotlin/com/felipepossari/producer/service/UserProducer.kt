package com.felipepossari.producer.service

import com.felipepossari.persistence.model.UserEntity
import com.felipepossari.producer.model.UserMessage

interface UserProducer {

    fun sendMessage(message: String, user: UserEntity): Boolean

    fun createUserCreatedMessage(user: UserEntity): String
    fun createUserUpdatedMessage(user: UserEntity): String
    fun createUserDeletedMessage(user: UserEntity): String
}
