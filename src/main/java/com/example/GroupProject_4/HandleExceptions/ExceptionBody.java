package com.example.GroupProject_4.HandleExceptions;

import org.springframework.http.HttpStatusCode;

import java.util.Date;

public class ExceptionBody {
    private String message;
    private HttpStatusCode httpStatusCode;

    private String whereIsProblem;
    private Date timeStamp;




    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public HttpStatusCode getHttpStatusCode() {
        return httpStatusCode;
    }

    public void setHttpStatusCode(HttpStatusCode httpStatusCode) {
        this.httpStatusCode = httpStatusCode;
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
