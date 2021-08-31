package ru.nosov.dry_cleaning.exceptions;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.util.Optional;

@ControllerAdvice
public class DryCleaningExceptionHandler {

    @ExceptionHandler({MethodArgumentTypeMismatchException.class})
    public ResponseEntity<ApiError> handleMethodArgumentTypeMismatch(
            MethodArgumentTypeMismatchException ex, WebRequest request) {
        String error = ex.getName() + " should be of type " + Optional.ofNullable(ex.getRequiredType())
                .map(Class::getName)
                .orElse("unknown");
        ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST, ex.getLocalizedMessage(), error);
        return new ResponseEntity<>(apiError, new HttpHeaders(), apiError.getStatus());
    }

    @ExceptionHandler({DryCleaningApiException.class})
    protected ResponseEntity<ApiError> handleDryCleaningInternalException(
            Exception ex, WebRequest request) {
        ApiError apiError =
                new ApiError(HttpStatus.BAD_REQUEST, ex.getLocalizedMessage(), ex.getMessage());
        return new ResponseEntity<>(apiError, new HttpHeaders(), apiError.getStatus());
    }


    public enum TypicalError {
        UNKNOWN(HttpStatus.INTERNAL_SERVER_ERROR),
        NOT_SUCH_ENTITY(HttpStatus.BAD_REQUEST);


        private HttpStatus status;

        TypicalError(HttpStatus status) {

            this.status = status;
        }

        public HttpStatus getStatus() {
            return status;
        }

        public void setStatus(HttpStatus status) {
            this.status = status;
        }
    }
}
