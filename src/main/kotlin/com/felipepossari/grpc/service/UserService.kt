package com.felipepossari.grpc.service

import com.felipepossari.UserCreateResponse
import com.felipepossari.grpc.model.UserDto

interface UserService {

    fun create(dto: UserDto): UserCreateResponse
}