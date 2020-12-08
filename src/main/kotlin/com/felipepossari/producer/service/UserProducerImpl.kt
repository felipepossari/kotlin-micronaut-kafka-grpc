package com.felipepossari.producer.service

import com.felipepossari.persistence.model.UserEntity
import com.felipepossari.producer.exception.ProducerException
import com.felipepossari.producer.model.EntityType
import com.felipepossari.producer.model.EventType
import com.felipepossari.producer.model.EventType.CREATED
import com.felipepossari.producer.model.EventType.DELETED
import com.felipepossari.producer.model.EventType.UPDATE
import io.micronaut.configuration.kafka.annotation.KafkaClient
import org.apache.kafka.clients.producer.Producer
import org.apache.kafka.clients.producer.ProducerRecord
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import java.time.Instant
import javax.inject.Singleton

@Singleton
class UserProducerImpl(private val userTopic: String,
                       private val builder: UserProducerBuilder,
                       private val parser: UserProducerParser,
                       @KafkaClient private val producer: Producer<String, String>) : UserProducer {

    companion object {
        val log: Logger = LoggerFactory.getLogger("ProductProducerHelper")
    }

    override fun sendMessage(message: String, user: UserEntity): Boolean {
        try {
            log.info("Sending message to kafka. User Id = ${user.id}")
            producer.send(ProducerRecord(userTopic, user.id.toString(), message))
        } catch (ex: ProducerException) {
            log.error("Error: ", ex)
            return false
        }
        return true
    }

    override fun createUserCreatedMessage(user: UserEntity): String {
        return createTopicMessage(user, CREATED)
    }

    override fun createUserUpdatedMessage(user: UserEntity): String {
        return createTopicMessage(user, UPDATE)
    }

    override fun createUserDeletedMessage(user: UserEntity): String {
        return createTopicMessage(user, DELETED)
    }

    private fun createTopicMessage(user: UserEntity, eventType: EventType): String {
        val userMessage = builder.buildUserMessage(user)
        val topicMessage = builder.builTopicMessage(parser.parseUserMessage(userMessage),
                EntityType.USER,
                eventType,
                Instant.now().toEpochMilli())
        return parser.parseTopicMessage(topicMessage)
    }


}
