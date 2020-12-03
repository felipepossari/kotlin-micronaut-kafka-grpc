package com.felipepossari.producer.service

import com.felipepossari.producer.model.UserMessage
import io.micronaut.configuration.kafka.annotation.KafkaClient
import io.micronaut.configuration.kafka.annotation.KafkaKey
import io.micronaut.configuration.kafka.annotation.Topic

@KafkaClient
interface UserKafkaClient {

    fun sendMessage(@Topic topic: String, @KafkaKey key: String, message: UserMessage)
}