package com.felipepossari.grpc.exception

import io.grpc.Status

enum class GrpcErrorReason(val errorCode: String,
                           val description: String,
                           val status: Status) {
    VAL("001", "eError", Status.INVALID_ARGUMENT)
}