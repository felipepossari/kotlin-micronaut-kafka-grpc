package com.felipepossari.producer.service

import com.felipepossari.persistence.model.UserEntity
import com.felipepossari.producer.model.UserMessage
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import javax.inject.Singleton

@Singleton
class UserProducerImpl(val userTopic: String,
                       val userKafkaClient: UserKafkaClient) : UserProducer {

    companion object {
        val log: Logger = LoggerFactory.getLogger("ProductProducerHelper")
    }

    override fun sendMessage(message: UserMessage): Boolean {
        log.info("Sending message to kafka. User Id = ${message.id}")
        userKafkaClient.sendMessage(userTopic, message.id.toString(), message)
        return true
    }

    override fun createMessage(user: UserEntity): UserMessage =
            UserMessage(id = user.id!!,
                    name = user.name,
                    email = user.email)
}