package com.felipepossari.grpc.service

import com.felipepossari.UserCreateResponse
import com.felipepossari.grpc.exception.GrpcErrorReason
import com.felipepossari.grpc.exception.GrpcErrorReason.FAILURE_TO_PUBLISH_USER_CREATED
import com.felipepossari.grpc.exception.GrpcException
import com.felipepossari.grpc.model.UserDto
import com.felipepossari.persistence.UserRepository
import com.felipepossari.persistence.model.UserEntity
import com.felipepossari.producer.service.UserProducer
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import javax.inject.Singleton

@Singleton
class UserServiceImpl(private val repository: UserRepository,
                      private val builder: UserBuilder,
                      private val producer: UserProducer,
                      private val wrapper: UserWrapper) : UserService {

    companion object{
        val log : Logger = LoggerFactory.getLogger(UserServiceImpl::class.qualifiedName)
    }

    override fun create(dto: UserDto): UserCreateResponse {
        var userEntity = builder.buildUser(dto)
        userEntity = repository.save(userEntity)
        if(wrapper.publishUserCreated(userEntity)) {
            return builder.buildCreateResponse(userEntity)
        }else{
            log.error(FAILURE_TO_PUBLISH_USER_CREATED.description)
            repository.delete(userEntity)
            throw GrpcException(FAILURE_TO_PUBLISH_USER_CREATED)
        }
    }

    private fun publishUserCreated(user: UserEntity) {
        val message = producer.createUserCreatedMessage(user)
        producer.sendMessage(message, user)
    }
}