package qa.data_provider.controller

import org.springframework.dao.DuplicateKeyException
import org.springframework.dao.EmptyResultDataAccessException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.HttpRequestMethodNotSupportedException
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.servlet.resource.NoResourceFoundException
import qa.data_provider.model.ErrorResponse

@ControllerAdvice
class GlobalException {

    @ExceptionHandler(EmptyResultDataAccessException::class)
    fun handleEmptyResultDataAccessException(ex: EmptyResultDataAccessException): ResponseEntity<ErrorResponse> {
        val errorResponse = ErrorResponse(
            message = "No result found for the given query",
            status = HttpStatus.NOT_FOUND.value()
        )
        return ResponseEntity(errorResponse, HttpStatus.NOT_FOUND)
    }


    @ExceptionHandler(NoResourceFoundException::class)
    fun handleNoResourceFoundException(ex: NoResourceFoundException): ResponseEntity<ErrorResponse> {
        val errorResponse = ErrorResponse(
            message = ex.message ?: "Resource not found",
            status = HttpStatus.NOT_FOUND.value()
        )
        return ResponseEntity(errorResponse, HttpStatus.NOT_FOUND)
    }


    @ExceptionHandler(HttpRequestMethodNotSupportedException::class)
    fun handleHttpRequestMethodNotSupportedException(ex: HttpRequestMethodNotSupportedException): ResponseEntity<ErrorResponse> {
        val errorResponse = ErrorResponse(
            message = "Request method not supported: ${ex.method}",
            status = HttpStatus.METHOD_NOT_ALLOWED.value()
        )
        return ResponseEntity(errorResponse, HttpStatus.METHOD_NOT_ALLOWED)
    }

    @ExceptionHandler(DuplicateKeyException::class)
    fun handleMongoDuplicateKeyException(ex: DuplicateKeyException): ResponseEntity<ErrorResponse> {
        val errorResponse = ErrorResponse(
            message = "Duplicate key error: ${ex.message?.substringAfter("dup key:")?.trim()}",
            status = HttpStatus.CONFLICT.value()
        )
        return ResponseEntity(errorResponse, HttpStatus.CONFLICT)
    }

}