package com.felipepossari.producer.service

import com.felipepossari.persistence.model.UserEntity
import com.felipepossari.producer.model.UserMessage

interface UserProducer {

    fun sendMessage(message: UserMessage): Boolean

    fun createMessage(user: UserEntity) : UserMessage
}