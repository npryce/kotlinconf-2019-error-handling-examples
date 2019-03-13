package example

interface HttpRequest {
    fun bodyToString(): String

    operator fun get(parameter: String): List<String> = etcetera
}

interface JsonNode {
    operator fun get(path: String): JsonNode
    fun asString(): String
}

interface HttpResponse

fun HttpResponse.body(body: Any?): HttpResponse = etcetera

fun HttpResponse(status: Int): HttpResponse = etcetera

