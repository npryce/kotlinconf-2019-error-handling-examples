@file:Suppress("unused_parameter", "unused")

package example.arrow

import arrow.core.Either
import arrow.core.Right
import arrow.core.extensions.either.monad.binding
import arrow.core.flatMap
import example.*

fun handlePost_nested(request: HttpRequest): Either<Error, HttpResponse> =
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

fun handlePost_flat(request: HttpRequest): Either<Error, HttpResponse> = binding {
    val (json) = request.readJson()
    val (command) = json.toCommand()
    val (resource) = loadResource(request)

    val (outcome) = performCommand(resource, command)

    outcome.toHttpResponseFor(request)
}


abstract class Command
abstract class Outcome

fun HttpRequest.readJson(): Either<Error, JsonNode> = etcetera
fun JsonNode.toCommand(): Either<Error, Command> = etcetera

interface Resource


fun loadResource(request: HttpRequest): Either<Error, Resource> = etcetera

sealed class ApplicationError

fun performCommand(resource: Resource, command: Command): Either<Error, Outcome> = etcetera

private fun Error.toHttpResponse(): HttpResponse = EXAMPLE
private fun Outcome.toHttpResponseFor(request: HttpRequest): HttpResponse = etcetera

fun Error.toHttpResponseFor(request: HttpRequest): HttpResponse = etcetera
