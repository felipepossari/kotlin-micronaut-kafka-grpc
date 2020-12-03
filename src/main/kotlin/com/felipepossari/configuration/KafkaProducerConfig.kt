package com.felipepossari.configuration

import io.micronaut.context.annotation.Bean
import io.micronaut.context.annotation.Factory
import io.micronaut.context.annotation.Property

@Factory
class KafkaProducerConfig(@Property(name = "kafka.topic.user")
                          val userTopic: String) {

    @Bean
    fun userTopic(): String {
        return userTopic
    }
}