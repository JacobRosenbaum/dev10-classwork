package learn.field_agent.models;

import learn.field_agent.domain.Result;
import learn.field_agent.domain.ResultType;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.sql.Timestamp;
import java.time.LocalDateTime;

public class ErrorResponse {

    private Timestamp timeStamp;
    private int status;
    private String error;
    private String message;
    private int ErrorResponseId;

    public ErrorResponse(Timestamp timeStamp, int status, String error, String message) {
        this.timeStamp = timeStamp;
        this.status = status;
        this.error = error;
        this.message = message;
    }

    public Timestamp getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(Timestamp timeStamp) {
        this.timeStamp = timeStamp;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getErrorResponseId() {
        return ErrorResponseId;
    }

    public void setErrorResponseId(int errorResponseId) {
        ErrorResponseId = errorResponseId;
    }
}
