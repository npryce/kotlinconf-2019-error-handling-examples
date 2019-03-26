@file:Suppress("unused", "UNUSED_PARAMETER")

package example.exceptions

import example.HttpRequest
import example.HttpResponse
import example.JsonNode
import example.etcetera
import java.math.BigInteger
import java.net.HttpURLConnection.HTTP_BAD_REQUEST
import java.net.HttpURLConnection.HTTP_OK
import java.util.NoSuchElementException


fun handlePost_1(request: HttpRequest): HttpResponse {
    val action = try {
        parseRequest_1(request)
    } catch (e: NumberFormatException) {
        return HttpResponse(HTTP_BAD_REQUEST)
    } catch (e: NoSuchElementException) {
        return HttpResponse(HTTP_BAD_REQUEST)
    }

    perform(action)
    return HttpResponse(HTTP_OK)
}

fun parseRequest_1(request: HttpRequest): BigInteger {
    val form = request.readForm()
    return form["id"]?.toBigInteger() ?: throw NoSuchElementException("id missing")
}

fun handlePost_2(request: HttpRequest): HttpResponse {
    val action = try {
        parseRequest_2(request)
    } catch (e: BadRequest) {
        return HttpResponse(HTTP_BAD_REQUEST)
    }

    perform(action)
    return HttpResponse(HTTP_OK)
}

fun parseRequest_2(request: HttpRequest) =
    try {
        val form = request.readForm()
        form["id"]?.toBigInteger() ?: throw BadRequest("id missing")
    } catch (e: NumberFormatException) {
        throw BadRequest(e)
    }


fun parseRequest_3(request: HttpRequest) =
    try {
        val json = request.readJson()
        json["id"].asString().toBigInteger()
    } catch (e: NumberFormatException) {
        throw BadRequest(e)
    }


class BadRequest(message: String?, cause: Exception? = null) :
    Exception(message, cause) {
    constructor(cause: Exception) : this(cause.message, cause)
}

fun HttpRequest.readJson(): JsonNode {
    etcetera
}


fun perform(resource: Any) {
    etcetera
}

fun representationOf(resource: Any): Any {
    etcetera
}

fun findResource(id: Int): Any {
    etcetera
}

private fun HttpRequest.readForm(): Map<String, String> {
    etcetera
}
