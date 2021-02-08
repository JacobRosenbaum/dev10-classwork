package learn.field_agent.data;

import learn.field_agent.models.ErrorResponse;

import java.util.List;

public interface ErrorResponseRepository {
    List<ErrorResponse> findAll();

    List<ErrorResponse> findByErrorCode(int errorCode);

    ErrorResponse add(ErrorResponse errorResponse);
}
