@file:Suppress("unused")

package example

import java.lang.NumberFormatException
import java.net.HttpURLConnection.*


fun handlePost(request: HttpRequest): HttpResponse {
    val action = try {
        parseRequest(request)
    } catch (e: BadRequest) {
        return HttpResponse(HTTP_BAD_REQUEST)
    }

    perform(action)
    return HttpResponse(HTTP_OK)
}

fun parseRequest(request: HttpRequest) =
    try {
        val form = request.readForm()
        form["id"]?.toBigInteger() ?: throw BadRequest("id missing")
    } catch(e: NumberFormatException) {
        throw BadRequest(e)
    }

fun parseRequest_2(request: HttpRequest) =
    try {
        val json = request.readJson()
        json["id"].asString().toBigInteger()
    } catch(e: NumberFormatException) {
        throw BadRequest(e)
    }


class BadRequest(message: String?, cause: Exception? = null):
    Exception(message, cause)
{
    constructor(cause: Exception): this(cause.message, cause)
}

interface HttpRequest {
    fun bodyToString(): String
}

fun HttpRequest.readJson(): JsonNode {
    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
}

private fun HttpRequest.readForm(): Map<String, String> {
    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
}

interface JsonNode {
    operator fun get(path: String): JsonNode
    fun asString(): String
}

interface HttpResponse {
    fun body(body: Any?): HttpResponse
}

fun HttpResponse(status: Int): HttpResponse {
    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
}


fun perform(resource: Any) {
    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
}

fun representationOf(resource: Any): Any {
    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
}

fun findResource(id: Int): Any {
    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
}

