package com.felipepossari.producer.service

import com.fasterxml.jackson.core.JsonProcessingException
import com.fasterxml.jackson.databind.ObjectMapper
import com.felipepossari.producer.exception.ProducerErrorReason
import com.felipepossari.producer.exception.ProducerErrorReason.FAILURE_TO_PARSE_TOPIC_MESSAGE
import com.felipepossari.producer.exception.ProducerErrorReason.FAILURE_TO_PARSE_USER_MESSAGE
import com.felipepossari.producer.exception.ProducerException
import com.felipepossari.producer.model.TopicMessage
import com.felipepossari.producer.model.UserMessage
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import javax.inject.Singleton

@Singleton
class UserProducerParser(private val objectMapper: ObjectMapper) {

    companion object {
        val log: Logger = LoggerFactory.getLogger(UserProducerParser::class.qualifiedName)
    }

    fun parseUserMessage(message: UserMessage): String {
        try {
            return objectMapper.writeValueAsString(message)
        } catch (ex: JsonProcessingException) {
            log.error(FAILURE_TO_PARSE_USER_MESSAGE.description)
            throw ProducerException(FAILURE_TO_PARSE_USER_MESSAGE)
        }
    }

    fun parseTopicMessage(topicMessage: TopicMessage): String {
        try {
            return objectMapper.writeValueAsString(topicMessage)
        } catch (ex: JsonProcessingException) {
            log.error(FAILURE_TO_PARSE_TOPIC_MESSAGE.description)
            throw ProducerException(FAILURE_TO_PARSE_TOPIC_MESSAGE)
        }
    }
}
