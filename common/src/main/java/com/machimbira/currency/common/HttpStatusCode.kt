package com.machimbira.currency.common

enum class HttpStatusCode private constructor(val value: Int) {
    OK(200),
    CREATED(201),
    BAD_REQUEST(400),
    UNAUTHORISED(401),
    NOT_FOUND(404),
    CONFLICT(409),
    NETWORK_CONNECT_TIMEOUT_ERROR(599)
}