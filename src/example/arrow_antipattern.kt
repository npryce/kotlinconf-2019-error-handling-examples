@file:Suppress("unused_parameter", "unused")

package example

import com.natpryce.*
import example.exceptions.HttpRequest
import example.exceptions.HttpResponse
import example.exceptions.JsonNode

fun handlePost_nested(request: HttpRequest): Result<HttpResponse,Error> =
    request.readJson()
        .flatMap { json ->
            json.toCommand()
                .flatMap { command ->
                    loadResource(request)
                        .flatMap { resource ->
                            performCommand(resource, command)
                                .map { outcome -> outcome.toHttpResponseFor(request) }
                        }
                }
        }

fun handlePost_flat(request: HttpRequest): Result<HttpResponse,Error> {
    val json = request.readJson().onFailure { return it }
    val command = json.toCommand().onFailure { return it }
    val resource = loadResource(request).onFailure { return it }
    val outcome = performCommand(resource, command).onFailure { return it }
    return Success(outcome.toHttpResponseFor(request))
}


abstract class Command
abstract class Outcome

fun HttpRequest.readJson(): Result<JsonNode, Error> = EXAMPLE
fun JsonNode.toCommand(): Result<Command, Error> = EXAMPLE

interface Resource

abstract class Error

fun loadResource(request: HttpRequest): Result<Resource, Error> = EXAMPLE

sealed class ApplicationError

fun performCommand(resource: Resource, command: Command): Result<Outcome, Error> = EXAMPLE

private fun Error.toHttpResponse(): HttpResponse = EXAMPLE
private fun Outcome.toHttpResponseFor(request: HttpRequest): HttpResponse = EXAMPLE
