package com.felipepossari.grpc.interceptor

import com.felipepossari.ErrorResponse
import com.felipepossari.UserCreateResponse
import com.felipepossari.grpc.exception.GrpcErrorReason
import com.felipepossari.grpc.exception.GrpcException
import io.grpc.ForwardingServerCall
import io.grpc.Metadata
import io.grpc.ServerCall
import io.grpc.ServerCallHandler
import io.grpc.ServerInterceptor
import io.grpc.Status
import io.grpc.protobuf.ProtoUtils
import io.micronaut.core.order.Ordered
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import javax.inject.Singleton

@Singleton
class ExceptionHandlerInterceptor : ServerInterceptor, Ordered {

    companion object {
        val log: Logger = LoggerFactory.getLogger(ExceptionHandlerInterceptor::class.simpleName)
    }

    override fun getOrder(): Int {
        return 1
    }

    override fun <ReqT : Any?, RespT : Any?> interceptCall(call: ServerCall<ReqT, RespT>, headers: Metadata, next: ServerCallHandler<ReqT, RespT>): ServerCall.Listener<ReqT> {
        log.info("ExceptionHandlerInterceptor executed -----------")
        return next.startCall(ExceptionHandlerServerCall(call), headers)
    }

    private class ExceptionHandlerServerCall<ReqT, RespT>(delegate: ServerCall<ReqT, RespT>) :
            ForwardingServerCall.SimpleForwardingServerCall<ReqT, RespT>(delegate) {

        override fun close(status: Status, trailers: Metadata) {
            if (status.isOk) {
                super.close(status, trailers)
            } else {
                super.close(buildErrorStatus(status), trailers)
            }
        }

        private fun buildErrorStatus(status: Status): Status {
            val exception = status.cause as? GrpcException ?: GrpcException(GrpcErrorReason.UNKNOWN_FAILURE)
            return exception.getStatus()
                    .withCause(status.cause)
                    .withDescription(exception.message)

        }
    }
}