package learn.solar.controllers;

import learn.solar.domain.PanelResult;
import learn.solar.domain.PanelService;
import learn.solar.domain.ResultType;
import learn.solar.models.Panel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/panels")
public class PanelController {
    private final PanelService service;

    // 3. Auto-inject PanelService
    public PanelController(PanelService service) {
        this.service = service;
    }

    @GetMapping
    public List<Panel> findAll() {
        return service.findAll();
    }

    @GetMapping("/{panelId}")
    public ResponseEntity<Panel> findById(@PathVariable int panelId) {
        Panel panel = service.findById(panelId);
        if (panel == null) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(panel, HttpStatus.OK);
    }

    @GetMapping("/section/{section}")
    public ResponseEntity<List<Panel>> findBySection(@PathVariable String section) {
        List<Panel> panels = service.findBySection(section);
        if (panels == null || panels.size() == 0) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(panels, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Panel> add(@RequestBody Panel panel) {
        PanelResult<Panel> result = service.add(panel);
        if (result.getType() != ResultType.SUCCESS) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(result.getPayload(), HttpStatus.CREATED);
    }

    @PutMapping("/{panelId}")
    public ResponseEntity<Panel> update(@PathVariable int panelId, @RequestBody Panel panel) {

        // id conflict. stop immediately.
        if (panelId != panel.getPanelId()) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }

        // 4. ResultType -> HttpStatus
        PanelResult<Panel> result = service.update(panel);
        if (result.getType() == ResultType.INVALID) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        } else if (result.getType() == ResultType.NOT_FOUND) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(result.getPayload(), HttpStatus.CREATED);
    }

    @DeleteMapping("/{panelId}")
    public ResponseEntity<Panel> delete(@PathVariable int panelId) {
        PanelResult<Panel> result = service.deleteById(panelId);

        if (result.getType() != ResultType.SUCCESS) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
