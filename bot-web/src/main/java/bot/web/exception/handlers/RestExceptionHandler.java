package bot.web.exception.handlers;


import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import bot.web.response.details.ErrorResponseDetails;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ValidationException;
import java.util.Calendar;


@ControllerAdvice(annotations = {RestController.class, Component.class})
@EnableWebMvc
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    private static final String NULL_FIELDS = "Validation for income data is failed. Check fields for NULL value";


    @ExceptionHandler(value = {ValidationException.class, RuntimeException.class})
    public ResponseEntity<ErrorResponseDetails> handleValidateExceptions(HttpServletRequest request, Exception e) {
        ErrorResponseDetails errorResponseDetails =
                new ErrorResponseDetails(
                        Calendar.getInstance().getTime(),
                        e.getMessage(),
                        request.getRequestURI()
                );
        return new ResponseEntity<>(errorResponseDetails, HttpStatus.BAD_REQUEST);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex,
            HttpHeaders headers,
            HttpStatus status,
            WebRequest request) {
        ErrorResponseDetails errorResponseDetails = new ErrorResponseDetails(Calendar.getInstance().getTime(), NULL_FIELDS + ": " + ex.getMessage(),
                request.getDescription(false));
        return new ResponseEntity<>(errorResponseDetails, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = Exception.class)
    public ResponseEntity<ErrorResponseDetails> handleException(HttpServletRequest request, Exception e) {
        ErrorResponseDetails errorResponseDetails =
                new ErrorResponseDetails(
                        Calendar.getInstance().getTime(),
                        e.getMessage(),
                        request.getRequestURI()
                );
        return new ResponseEntity<>(errorResponseDetails, HttpStatus.INTERNAL_SERVER_ERROR);
    }

}

