package com.felipepossari.producer.model

class TopicMessage(val eventType: String,
                   val entityType: String,
                   val message: String,
                   val timestamp: Long) {}
