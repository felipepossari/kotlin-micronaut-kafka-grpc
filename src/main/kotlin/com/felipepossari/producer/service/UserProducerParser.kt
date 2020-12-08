package com.felipepossari.producer.service

import com.fasterxml.jackson.databind.ObjectMapper
import com.felipepossari.producer.model.TopicMessage
import com.felipepossari.producer.model.UserMessage
import javax.inject.Singleton

@Singleton
class UserProducerParser(private val objectMapper: ObjectMapper) {

    fun parseUserMessage(message: UserMessage) : String {
        return objectMapper.writeValueAsString(message)
    }

    fun parseTopicMessage(topicMessage: TopicMessage) : String{
        return objectMapper.writeValueAsString(topicMessage)
    }
}
