package com.felipepossari.producer.service

import com.felipepossari.persistence.model.UserEntity
import com.felipepossari.producer.exception.ProducerException

interface UserProducer {

    @Throws(ProducerException::class)
    fun sendMessage(message: String, user: UserEntity): Boolean

    @Throws(ProducerException::class)
    fun createUserCreatedMessage(user: UserEntity): String

    @Throws(ProducerException::class)
    fun createUserUpdatedMessage(user: UserEntity): String

    @Throws(ProducerException::class)
    fun createUserDeletedMessage(user: UserEntity): String
}
