package com.felipepossari.producer.service

import com.felipepossari.persistence.model.UserEntity
import com.felipepossari.producer.model.EntityType
import com.felipepossari.producer.model.EventType
import com.felipepossari.producer.model.TopicMessage
import com.felipepossari.producer.model.UserMessage
import org.hibernate.usertype.UserType
import javax.inject.Singleton

@Singleton
class UserProducerBuilder {

    fun buildUserMessage(user: UserEntity) =
            UserMessage(id = user.id!!,
                    name = user.name,
                    email = user.email)

    fun builTopicMessage(message: String,
                         entityType: EntityType,
                         eventType: EventType,
                         timestamp: Long) =
            TopicMessage(eventType = eventType.name,
                    entityType = entityType.name,
                    message = message,
                    timestamp = timestamp)
}
