@file:Suppress("unused", "UNUSED_VARIABLE")

package example

import java.net.HttpURLConnection.HTTP_BAD_REQUEST
import java.time.Instant
import java.time.format.DateTimeFormatter
import java.time.format.DateTimeFormatter.ISO_INSTANT
import java.time.format.DateTimeParseException


fun DateTimeFormatter.parseInstant(s: String): Instant? =
    try {
        parse(s, Instant::from)
    } catch (e: DateTimeParseException) {
        null
    }


fun handleGet_simple(request: HttpRequest): HttpResponse {
    val count = request["count"].firstOrNull()?.toIntOrNull()
        ?: return HttpResponse(HTTP_BAD_REQUEST).body("invalid count")

    val startTime = request["from"].firstOrNull()
        ?.let { ISO_INSTANT.parseInstant(it) }
        ?: return HttpResponse(HTTP_BAD_REQUEST).body("invalid from time")

    etcetera
}

fun handleGet_correct(request: HttpRequest): HttpResponse {
    val count = request["count"].firstOrNull()?.let {
        it.toIntOrNull()
            ?: return HttpResponse(HTTP_BAD_REQUEST).body("invalid count")
    } ?: 100

    val startTime = request["from"].firstOrNull()?.let {
        ISO_INSTANT.parseInstant(it)
            ?: return HttpResponse(HTTP_BAD_REQUEST).body("invalid from time")
    } ?: Instant.now()

    etcetera
}

