package learn.field_agent.controllers;

import learn.field_agent.domain.Result;
import learn.field_agent.domain.ResultType;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
    // DataAccessException is the super class of many Spring database exceptions
    // including BadSqlGrammarException.
    @ExceptionHandler(DataAccessException.class)
    public ResponseEntity<ErrorResponse> handleException(DataAccessException ex) {
        System.out.println(ex);
        Result result = new Result();
        result.addMessage("We can't show you the details, but something went wrong in our database. Sorry :(", ResultType.SERVER_ERROR);
        return ErrorResponse.build(result);
    }

    // IllegalArgumentException is the super class for many Java exceptions
    // including all formatting (number, date) exceptions.
    @ExceptionHandler({IllegalArgumentException.class, HttpMessageNotReadableException.class})
    public ResponseEntity<ErrorResponse> handleException(IllegalArgumentException ex) {
        System.out.println(ex);
        return new ResponseEntity(
                ex,
                HttpStatus.BAD_REQUEST
        );
    }

    // This is our absolute last resort. Yuck.
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleException(Exception ex) {
        System.out.println(ex);
        Result result = new Result();
        result.addMessage("Something went wrong on our end. Your request failed. :(", ResultType.SERVER_ERROR);
        return ErrorResponse.build(result);
    }

}
