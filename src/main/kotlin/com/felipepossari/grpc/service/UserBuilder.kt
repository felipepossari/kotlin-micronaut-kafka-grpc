package com.felipepossari.grpc.service

import com.felipepossari.UserCreateRequest
import com.felipepossari.UserCreateResponse
import com.felipepossari.grpc.model.UserDto
import com.felipepossari.persistence.model.UserEntity
import javax.inject.Singleton

@Singleton
class UserBuilder {

    fun buildDto(userRequest: UserCreateRequest): UserDto {
        return UserDto(
                email = userRequest.email,
                name = userRequest.name)
    }

    fun buildUser(dto: UserDto): UserEntity {
        return UserEntity(
                email = dto.email,
                name = dto.name)
    }

    fun buildCreateResponse(user: UserEntity): UserCreateResponse {
        return UserCreateResponse.newBuilder()
                .setId(user.id!!)
                .setName(user.name)
                .setEmail(user.email)
                .build()
    }
}