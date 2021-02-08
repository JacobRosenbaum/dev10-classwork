package learn.field_agent.controllers;

import learn.field_agent.domain.LocationService;
import learn.field_agent.domain.Result;
import learn.field_agent.models.Location;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = {"http://localhost:3000"})
@RequestMapping("/api/location")
public class LocationController {

    private final LocationService service;

    public LocationController(LocationService service) {
        this.service = service;
    }

    @GetMapping("/{locationId}")
    public ResponseEntity<Location> findById(@PathVariable int locationId) {
        if (locationId <= 0) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }

       Location location = service.findById(locationId);
        if (location == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(location, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Object> add(@RequestBody Location location) {
        Result<Location> result = service.add(location);
        if (!result.isSuccess()) {
            return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(result.getPayload(), HttpStatus.CREATED);
    }

    @PutMapping("/{locationId}")
    public ResponseEntity<Object> update(@PathVariable int locationId, @RequestBody Location location) {
        if (locationId != location.getLocationId()) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }

        Result<Location> result = service.update(location);
        if (!result.isSuccess()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(result.getPayload(), HttpStatus.OK);
    }

    @DeleteMapping("/{locationId}")
    public ResponseEntity<Void> deleteById(@PathVariable int locationId) {
        if (service.deleteById(locationId)) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
