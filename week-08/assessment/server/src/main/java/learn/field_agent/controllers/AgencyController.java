package learn.field_agent.controllers;

import learn.field_agent.domain.AgencyService;
import learn.field_agent.domain.Result;
import learn.field_agent.models.Agency;
import learn.field_agent.models.Agent;
import learn.field_agent.models.SecurityClearance;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = {"http://localhost:3000"})
@RequestMapping("/api/agency")
public class AgencyController {

    private final AgencyService agencyService;

    public AgencyController(AgencyService agencyService) {
        this.agencyService = agencyService;
    }

    @GetMapping
    public List<Agency> findAll() {
        return agencyService.findAll();
    }

    @GetMapping("/{agencyId}")
    public ResponseEntity<Agency> findById(@PathVariable int agencyId) {
        if (agencyId <= 0) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }

        Agency agency = agencyService.findById(agencyId);
        if (agency == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(agency, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Object> add(@RequestBody Agency agency) {
        Result<Agency> result = agencyService.add(agency);
        if (!result.isSuccess()) {
            return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(result.getPayload(), HttpStatus.CREATED);

    }

    @PutMapping("/{agencyId}")
    public ResponseEntity<Object> update(@PathVariable int agencyId, @RequestBody Agency agency) {
        if (agencyId != agency.getAgencyId()) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }

        Result<Agency> result = agencyService.update(agency);
        if (!result.isSuccess()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(result.getPayload(), HttpStatus.OK);
    }

    @DeleteMapping("/{agencyId}")
    public ResponseEntity<Void> deleteById(@PathVariable int agencyId) {
        if (agencyService.deleteById(agencyId)) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
