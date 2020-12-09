package com.felipepossari.grpc.service

import com.felipepossari.persistence.model.UserEntity
import com.felipepossari.producer.exception.ProducerException
import com.felipepossari.producer.service.UserProducer
import javax.inject.Singleton

@Singleton
class UserWrapper(private val producer: UserProducer) {

    fun publishUserCreated(user: UserEntity) : Boolean{
        try {
            val message = producer.createUserCreatedMessage(user)
            return producer.sendMessage(message, user)
        }catch (ex: ProducerException){
            return false
        }
    }


}