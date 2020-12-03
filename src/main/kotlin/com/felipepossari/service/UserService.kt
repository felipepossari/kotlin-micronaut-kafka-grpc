package com.felipepossari.service

import com.felipepossari.UserCreateResponse
import com.felipepossari.model.UserDto

interface UserService {

    fun create(dto: UserDto): UserCreateResponse
}