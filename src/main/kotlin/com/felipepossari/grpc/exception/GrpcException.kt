package com.felipepossari.grpc.exception

import io.grpc.Status

class GrpcException(private val error: GrpcErrorReason) : Exception(error.description) {

    override val message: String?
        get() = "${error.errorCode} - ${error.description}"

    fun getStatus() : Status = error.status
}