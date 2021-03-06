package com.felipepossari.grpc.entrypoint

import com.felipepossari.KafkaServiceGrpc
import com.felipepossari.UserCreateRequest
import com.felipepossari.UserCreateResponse
import com.felipepossari.grpc.exception.GrpcException
import com.felipepossari.grpc.model.UserDto
import com.felipepossari.grpc.service.UserService
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
        try {
            log.info("Creating user")
            val userDto = UserDto(email = request.email, name = request.name)
            responseObserver.onNext(service.create(userDto))
            responseObserver.onCompleted()
        } catch (ex: GrpcException){
            responseObserver.onError(ex)
        }
    }
}
