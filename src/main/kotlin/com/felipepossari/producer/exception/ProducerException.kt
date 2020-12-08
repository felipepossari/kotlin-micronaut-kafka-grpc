package com.felipepossari.producer.exception

class ProducerException(private val errorReason: ProducerErrorReason) : Exception(errorReason.description) {
}
