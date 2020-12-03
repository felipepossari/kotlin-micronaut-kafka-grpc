package com.felipepossari.producer.service

import com.felipepossari.persistence.model.UserEntity
import com.felipepossari.producer.model.UserMessage
import io.micronaut.configuration.kafka.annotation.KafkaClient
import org.apache.kafka.clients.producer.Producer
import org.apache.kafka.clients.producer.ProducerRecord
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import javax.inject.Singleton

@Singleton
class UserProducerImpl(private val userTopic: String,
                       @KafkaClient private val producer: Producer<String, UserMessage>) : UserProducer {

    companion object {
        val log: Logger = LoggerFactory.getLogger("ProductProducerHelper")
    }

    override fun sendMessage(message: UserMessage): Boolean {
        try {
            log.info("Sending message to kafka. User Id = ${message.id}")
            producer.send(ProducerRecord(userTopic, message.id.toString(), message))
        } catch (ex: Exception){
            log.error("Error: ", ex)
            return false
        }
        return true
    }

    override fun createMessage(user: UserEntity): UserMessage =
            UserMessage(id = user.id!!,
                    name = user.name,
                    email = user.email)
}