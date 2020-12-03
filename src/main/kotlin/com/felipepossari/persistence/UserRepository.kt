package com.felipepossari.persistence

import com.felipepossari.persistence.model.UserEntity
import io.micronaut.data.annotation.Repository
import io.micronaut.data.repository.CrudRepository

@Repository
interface UserRepository : CrudRepository<UserEntity, Long> {
}