package com.appsdeveloperblock.app.ws.exceptions;

import com.appsdeveloperblock.app.ws.ui.model.response.ErrorMessage;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;

@ControllerAdvice // important because it will listen to the exceptions that take place in the application across all mappings
public class AppExceptionHandler extends ResponseEntityExceptionHandler {

    // exception class is required. if a specific exception is used, the exception is mentioned.

    /*  to handle general exceptions. */

    @ExceptionHandler(value = {Exception.class})  //  method that handles the exception (handleAnyException), to do this method is annotated with exceptionhandler.
    public ResponseEntity<Object> handleAnyException(Exception ex , WebRequest request) {
        // needs to return a response hence the response entity which can contain body of the response, messages etc


        String errorMessageDescp = ex.getLocalizedMessage();

        if(errorMessageDescp == null) errorMessageDescp = ex.toString(); // sometimes error messages can be null, hence we use to string to show the null as a string.

        ErrorMessage errorMessage = new ErrorMessage(new Date(),errorMessageDescp); // add the variable to the constructor

        //return new ResponseEntity<>(ex, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR); // the entire exception stack trace is included as the body of the response

        return new ResponseEntity<>(errorMessage, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR); // now the framework will return the error message in json or xml based on accept header in postman
    }

    /* to handle a specific exception - null pointer exception */

    // if you want a method to handle more than one exception, append the exception.class in the value.

    @ExceptionHandler(value = {NullPointerException.class,UserServiceException.class})  //  method that handles the exception (handleAnyException), to do this method is annotated with exceptionhandler.
    public ResponseEntity<Object> handleSpecificException(Exception ex , WebRequest request) {  // needs to return a response hence the response entity which can contain body of the response, messages etc


        String errorMessageDescp = ex.getLocalizedMessage();

        if(errorMessageDescp == null) errorMessageDescp = ex.toString(); // sometimes error messages can be null, hence we use to string to show the null as a string.

        ErrorMessage errorMessage = new ErrorMessage(new Date(),errorMessageDescp); // add the variable to the constructor

        //return new ResponseEntity<>(ex, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR); // the entire exception stack trace is included as the body of the response

        return new ResponseEntity<>(errorMessage, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR); // now the framework will return the error message in json or xml based on accept header in postman
    }


    /* to handle a user service exception */

    /*

    @ExceptionHandler(value = {UserServiceException.class})  //  method that handles the exception (handleAnyException), to do this method is annotated with exceptionhandler.
    public ResponseEntity<Object> handleUserServiceException(UserServiceException ex , WebRequest request) {  // needs to return a response hence the response entity which can contain body of the response, messages etc


        String errorMessageDescp = ex.getLocalizedMessage();

        if(errorMessageDescp == null) errorMessageDescp = ex.toString(); // sometimes error messages can be null, hence we use to string to show the null as a string.

        ErrorMessage errorMessage = new ErrorMessage(new Date(),errorMessageDescp); // add the variable to the constructor

        //return new ResponseEntity<>(ex, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR); // the entire exception stack trace is included as the body of the response

        return new ResponseEntity<>(errorMessage, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR); // now the framework will return the error message in json or xml based on accept header in postman
    }


     */
}
