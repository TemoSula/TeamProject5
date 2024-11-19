package com.example.GroupProject_4.HandleExceptions;

import com.example.GroupProject_4.Exceptions.CommentNotFound;
import com.example.GroupProject_4.Exceptions.PostNotFound;
import com.example.GroupProject_4.Exceptions.UserAlreadyExsist;
import com.example.GroupProject_4.Exceptions.UserNotFound;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestControllerAdvice
public class GlobaExceptoinHandles {


    /*@ExceptionHandler(RuntimeException.class)
    public ResponseEntity<?> handleRuntimeException(RuntimeException runtimeException)
    {


        StackTraceElement[] stackTraceElements = runtimeException.getStackTrace();
        StackTraceElement stackTraceElement = stackTraceElements[0];
        String className = stackTraceElement.getMethodName();



        ExceptionBody exb = new ExceptionBody();
        exb.setMessage(runtimeException.getMessage());
        exb.setWhereIsProblem(className);
        exb.setTimeStamp(new Date(System.currentTimeMillis()));
        exb.setHttpStatusCode(ResponseEntity.status(HttpStatus.BAD_REQUEST).build().getStatusCode());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exb);
    }*/

    @ExceptionHandler(UserNotFound.class)
    public ResponseEntity<?> handleUserNotFoundException(UserNotFound unf)
    {
        StackTraceElement[] stackTraceElements = unf.getStackTrace();
        StackTraceElement stackTraceElement = stackTraceElements[0];
        String className = stackTraceElement.getMethodName();

        ExceptionBody exb = new ExceptionBody();
        exb.setMessage(unf.getMessage());
        exb.setWhereIsProblem(className);
        exb.setTimeStamp(new Date(System.currentTimeMillis()));
        exb.setHttpStatusCode(ResponseEntity.status(HttpStatus.BAD_REQUEST).build().getStatusCode());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exb);
    }

    @ExceptionHandler(UserAlreadyExsist.class)
    public ResponseEntity<?> UserAlreadyExsistException(UserAlreadyExsist userAlreadyExsist)
    {
        StackTraceElement[] stackTraceElements = userAlreadyExsist.getStackTrace();
        StackTraceElement stackTraceElement = stackTraceElements[0];
        String className = stackTraceElement.getMethodName();

        ExceptionBody exb = new ExceptionBody();
        exb.setMessage(userAlreadyExsist.getMessage());
        exb.setWhereIsProblem(className);
        exb.setTimeStamp(new Date(System.currentTimeMillis()));
        exb.setHttpStatusCode(ResponseEntity.status(HttpStatus.BAD_REQUEST).build().getStatusCode());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exb);
    }

    @ExceptionHandler(PostNotFound.class)
    public ResponseEntity<?> PostNotFoundException(PostNotFound postNotFound)
    {
        StackTraceElement[] stackTraceElements = postNotFound.getStackTrace();
        StackTraceElement stackTraceElement = stackTraceElements[0];
        String className = stackTraceElement.getMethodName();

        ExceptionBody exb = new ExceptionBody();
        exb.setMessage(postNotFound.getMessage());
        exb.setWhereIsProblem(className);
        exb.setTimeStamp(new Date(System.currentTimeMillis()));
        exb.setHttpStatusCode(ResponseEntity.status(HttpStatus.BAD_REQUEST).build().getStatusCode());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exb);
    }

    @ExceptionHandler(CommentNotFound.class)
    public ResponseEntity<?> handleCommentNotFounndException(CommentNotFound commentNotFound)
    {
        StackTraceElement[] stackTraceElements = commentNotFound.getStackTrace();
        StackTraceElement stackTraceElement = stackTraceElements[0];
        String className = stackTraceElement.getMethodName();

        ExceptionBody exb = new ExceptionBody();
        exb.setMessage(commentNotFound.getMessage());
        exb.setWhereIsProblem(className);
        exb.setTimeStamp(new Date(System.currentTimeMillis()));
        exb.setHttpStatusCode(ResponseEntity.status(HttpStatus.BAD_REQUEST).build().getStatusCode());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exb);
    }


    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleMethodArgumentNotValidException(MethodArgumentNotValidException methodArg)
    {
        MethodAgumentNotValidExceptionBody eb = new MethodAgumentNotValidExceptionBody();
        List<FieldError> fe = methodArg.getFieldErrors();
        List<String> erroredFields = new ArrayList<>();
        for (FieldError fieldError: fe)
        {
            erroredFields.add(fieldError.getField() + ":" + fieldError.getDefaultMessage());
        }
        StackTraceElement[] stackTraceElements = methodArg.getStackTrace();
        StackTraceElement stackTraceElement = stackTraceElements[0];
        String methodName = stackTraceElement.getMethodName();

        eb.setMessage(erroredFields);
        eb.setHttpStatus(methodArg.getStatusCode());
        eb.setWhereIsProblem(methodName);
        eb.setTimeStamp(new Date(System.currentTimeMillis()));


        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(eb);
    }

    @ExceptionHandler(DateTimeParseException.class)
    public ResponseEntity<?> handleInvalidDateFormat(DateTimeParseException dateTimeParseException) {

        StackTraceElement[] stackTraceElements = dateTimeParseException.getStackTrace();
        StackTraceElement stackTraceElement = stackTraceElements[0];
        String className = stackTraceElement.getMethodName();

        ExceptionBody exb = new ExceptionBody();
        exb.setMessage("date time format is yyyy-mm-dd");
        exb.setWhereIsProblem(className);
        exb.setTimeStamp(new Date(System.currentTimeMillis()));
        exb.setHttpStatusCode(ResponseEntity.status(HttpStatus.BAD_REQUEST).build().getStatusCode());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exb);
    }

}




