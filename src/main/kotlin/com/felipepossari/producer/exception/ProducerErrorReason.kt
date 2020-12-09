package com.felipepossari.producer.exception

enum class ProducerErrorReason(val description: String) {
    FAILURE_TO_PARSE_USER_MESSAGE("Failure to parse the user message."),
    FAILURE_TO_PARSE_TOPIC_MESSAGE("Failure to parse the topic message."),
    FAILURE_TO_PUBLISH_MESSAGE("Failure to publish the message.")
}