package example.results

import com.natpryce.Failure
import com.natpryce.Result
import com.natpryce.Success
import example.etcetera

class Value
class Error

fun operationThatCanFail(): Result<Value, Error> = etcetera
fun doSomethingWith(value: Value): Unit = etcetera
fun handleError(reason: Error): Unit = etcetera

fun main() {

    val result = operationThatCanFail()
    when (result) {
        is Success<Value> -> doSomethingWith(result.value)
        is Failure<Error> -> handleError(result.reason)
    }
}
