package com.felipepossari.service

import com.felipepossari.UserCreateRequest
import com.felipepossari.UserCreateResponse
import com.felipepossari.model.UserDto
import com.felipepossari.persistence.UserRepository
import com.felipepossari.persistence.model.UserEntity
import com.felipepossari.producer.service.UserProducer
import javax.inject.Singleton

@Singleton
class UserServiceImpl(private val repository: UserRepository,
                      private val builder: UserBuilder,
                      private val producer: UserProducer) : UserService {
    override fun create(dto: UserDto): UserCreateResponse {
        var userEntity = builder.buildUser(dto)
        userEntity = repository.save(userEntity)
        publishUserCreated(userEntity)

        return builder.buildCreateResponse(userEntity)
    }

    private fun publishUserCreated(user : UserEntity){
        val message = producer.createMessage(user)
        producer.sendMessage(message)
    }
}