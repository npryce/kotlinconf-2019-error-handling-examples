@file:Suppress("unused_parameter", "unused")

package example.results

import com.natpryce.*
import example.*


fun handlePost_simple(request: HttpRequest): HttpResponse =
    request.readJson()
        .flatMap { json -> json.toCommand() }
        .flatMap { command -> performCommand(command) }
        .map { commandOutcome -> commandOutcome.toHttpResponseFor(request) }
        .mapFailure { error -> error.toHttpResponseFor(request) }
        .get()


fun performCommand(resource: Command): Result<Outcome, Error> =
    etcetera


fun handlePost_nested(request: HttpRequest): Result<HttpResponse, Error> =
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

fun handlePost_flat(request: HttpRequest): Result<HttpResponse, Error> {
    val json = request.readJson().onFailure { return it }
    val command = json.toCommand().onFailure { return it }
    val resource = loadResource(request).onFailure { return it }

    val outcome = performCommand(resource, command).onFailure { return it }

    return Success(outcome.toHttpResponseFor(request))
}


abstract class Command
abstract class Outcome

fun HttpRequest.readJson(): Result<JsonNode, Error> = etcetera
fun JsonNode.toCommand(): Result<Command, Error> = etcetera

interface Resource


fun loadResource(request: HttpRequest): Result<Resource, Error> = etcetera

sealed class ApplicationError

fun performCommand(resource: Resource, command: Command): Result<Outcome, Error> = etcetera

private fun Error.toHttpResponse(): HttpResponse = EXAMPLE
private fun Outcome.toHttpResponseFor(request: HttpRequest): HttpResponse = etcetera

fun Error.toHttpResponseFor(request: HttpRequest): HttpResponse = etcetera
