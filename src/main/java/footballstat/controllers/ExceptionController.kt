package footballstat.controllers

import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus
import javax.servlet.http.HttpServletRequest


@ControllerAdvice
open class ExceptionController
{
    private val logger = LoggerFactory.getLogger(this.javaClass)

    @ExceptionHandler
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    fun logException(request : HttpServletRequest, e : Exception)
    {
        logger.error("Handle exception from " + request.requestURL, e)
    }
}