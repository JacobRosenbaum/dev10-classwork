package learn.field_agent.domain;

import learn.field_agent.data.AliasRepository;
import learn.field_agent.data.ErrorResponseRepository;
import learn.field_agent.models.Alias;
import learn.field_agent.models.ErrorResponse;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;

import java.util.List;

@Service
public class ErrorResponseService {

    private final ErrorResponseRepository repository;

    public ErrorResponseService(ErrorResponseRepository repository) {
        this.repository = repository;
    }

    public List<ErrorResponse> findAll() {
        return repository.findAll();
    }

    public List<ErrorResponse> findByErrorCode(int errorCode) {
        return repository.findByErrorCode(errorCode);
    }

    public Result<ErrorResponse> add(ErrorResponse errorResponse) {
        Result<ErrorResponse> result = new Result<>();
        errorResponse = repository.add(errorResponse);
        result.setPayload(errorResponse);
        return result;
    }
}
