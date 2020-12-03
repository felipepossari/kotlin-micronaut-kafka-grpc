package com.felipepossari.entrypoint

import com.felipepossari.KafkaServiceGrpc
import com.felipepossari.UserCreateRequest
import com.felipepossari.UserCreateResponse
import com.felipepossari.model.UserDto
import com.felipepossari.persistence.model.UserEntity
import com.felipepossari.producer.service.UserProducer
import com.felipepossari.service.UserService
import io.grpc.stub.StreamObserver
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import javax.inject.Singleton

@Singleton
class GrpcEntrypoint(private val service: UserService)
    : KafkaServiceGrpc.KafkaServiceImplBase() {

    companion object {
        val log: Logger = LoggerFactory.getLogger("GrpcEntrypoint")
    }

    override fun createUser(request: UserCreateRequest, responseObserver: StreamObserver<UserCreateResponse>) {
        log.info("Creating user")
        val userDto = UserDto(email = request.email, name = request.name)
        responseObserver.onNext(service.create(userDto))
        responseObserver.onCompleted()
    }
}
