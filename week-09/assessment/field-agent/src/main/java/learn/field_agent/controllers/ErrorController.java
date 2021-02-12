package learn.field_agent.controllers;

import learn.field_agent.domain.ErrorResponseService;
import learn.field_agent.models.Alias;
import learn.field_agent.models.ErrorResponse;
import learn.field_agent.models.Location;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = {"http://localhost:3000"})
@RequestMapping("/api/errors")
public class ErrorController {
    private final ErrorResponseService service;

    public ErrorController(ErrorResponseService service) {
        this.service = service;
    }

    @GetMapping
    public List<ErrorResponse> findAll() {
        return service.findAll();
    }

    @GetMapping("/{errorCode}")
    public ResponseEntity<List<ErrorResponse>> findById(@PathVariable int errorCode) {
        if (errorCode <= 0) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
        List<ErrorResponse> all = service.findByErrorCode(errorCode);
        if (all == null || all.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(all, HttpStatus.OK);
    }
}
