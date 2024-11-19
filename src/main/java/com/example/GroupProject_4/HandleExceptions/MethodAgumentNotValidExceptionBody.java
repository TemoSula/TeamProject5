package com.example.GroupProject_4.HandleExceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;

import java.util.Date;
import java.util.List;

public class MethodAgumentNotValidExceptionBody {

    private List<String> message;
    private HttpStatusCode httpStatus;

    private String whereIsProblem;
    private Date timeStamp;

    public List<String> getMessage() {
        return message;
    }

    public void setMessage(List<String> message) {
        this.message = message;
    }

    public HttpStatusCode getHttpStatus() {
        return httpStatus;
    }

    public void setHttpStatus(HttpStatusCode httpStatus) {
        this.httpStatus = httpStatus;
    }

    public String getWhereIsProblem() {
        return whereIsProblem;
    }

    public void setWhereIsProblem(String whereIsProblem) {
        this.whereIsProblem = whereIsProblem;
    }

    public Date getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(Date timeStamp) {
        this.timeStamp = timeStamp;
    }
}
