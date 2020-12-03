package com.felipepossari.consumer

import io.micronaut.configuration.kafka.annotation.KafkaKey
import io.micronaut.configuration.kafka.annotation.KafkaListener
import io.micronaut.configuration.kafka.annotation.OffsetReset
import io.micronaut.configuration.kafka.annotation.Topic
import org.slf4j.Logger
import org.slf4j.LoggerFactory

@KafkaListener(offsetReset = OffsetReset.EARLIEST)
class UserListener {

    companion object{
        val log : Logger = LoggerFactory.getLogger(UserListener.javaClass.simpleName)
    }

    @Topic("user")
    fun receive(@KafkaKey key: String, message: String){
        log.info("Message received: $message")
    }
}