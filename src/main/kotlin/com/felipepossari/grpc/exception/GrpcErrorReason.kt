package com.felipepossari.grpc.exception

import io.grpc.Status

enum class GrpcErrorReason(val errorCode: String,
                           val description: String,
                           val status: Status) {
    FAILURE_TO_PUBLISH_USER_CREATED("001", "Failure to publish user created message.", Status.ABORTED),
    UNKNOWN_FAILURE("999", "Unknown failure.", Status.UNKNOWN)
}