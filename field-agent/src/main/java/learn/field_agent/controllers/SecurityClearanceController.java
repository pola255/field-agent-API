package learn.field_agent.controllers;


import learn.field_agent.domain.Result;
import learn.field_agent.domain.SecurityClearanceService;
import learn.field_agent.models.SecurityClearance;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = {"http://localhost:3000"})
@RequestMapping("/api/security-clearance")

public class SecurityClearanceController {
    private final SecurityClearanceService securityClearanceService;

    public SecurityClearanceController(SecurityClearanceService securityClearanceService) {
        this.securityClearanceService = securityClearanceService;
    }

    @GetMapping
    public List<SecurityClearance> findAll() {
        return securityClearanceService.findAll();
    }

    @GetMapping("/{securityClearanceId}")
    public ResponseEntity<SecurityClearance> findById(@PathVariable int securityClearanceId) {
        SecurityClearance securityClearance = securityClearanceService.findById(securityClearanceId);
        if (securityClearance == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(securityClearance);
    }

    @PostMapping
    public ResponseEntity<Object> add(@RequestBody SecurityClearance securityClearance) {
        Result<SecurityClearance> result = securityClearanceService.add(securityClearance);
        if (result.isSuccess()) {
            return new ResponseEntity<>(result.getPayload(), HttpStatus.CREATED);
        }
        return ErrorResponse.build(result);
    }

    @PutMapping("/{securityClearanceId}")
    public ResponseEntity<Object> update(@PathVariable int securityClearanceId, @RequestBody SecurityClearance securityClearance) {
        if (securityClearanceId != securityClearance.getSecurityClearanceId()) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }

        Result<SecurityClearance> result = securityClearanceService.update(securityClearance);
        if (result.isSuccess()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return ErrorResponse.build(result);
    }

    @DeleteMapping("/{securityClearanceId}")
    public ResponseEntity<Void> deleteById(@PathVariable int securityClearanceId) {
        if (securityClearanceService.deleteById(securityClearanceId)) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
