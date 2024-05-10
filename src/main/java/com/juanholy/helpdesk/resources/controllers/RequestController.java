package com.juanholy.helpdesk.resources.controllers;

import com.juanholy.helpdesk.domain.Request;
import com.juanholy.helpdesk.domain.dtos.RequestDTO;
import com.juanholy.helpdesk.domain.dtos.RequestResponseDTO;
import com.juanholy.helpdesk.resources.services.RequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/requests")
public class RequestController {
    @Autowired
    private RequestService service;

    @GetMapping
    public ResponseEntity<List<RequestResponseDTO>> findAll() {
        List<RequestResponseDTO> list = service.findAll();
        return ResponseEntity.ok(list);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RequestResponseDTO> findById(@PathVariable  Long id) {
        RequestResponseDTO entity = service.findById(id);
        return ResponseEntity.ok(entity);
    }

    @PostMapping
    public ResponseEntity<RequestResponseDTO> insert(@RequestBody RequestDTO obj) {
        RequestResponseDTO entity = service.insert(obj);
        return ResponseEntity.status(HttpStatus.CREATED).body(entity);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.badRequest().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<RequestResponseDTO> update(@PathVariable Long id, @RequestBody RequestDTO obj) {
        RequestResponseDTO entity = service.update(id, obj);
        return ResponseEntity.status(HttpStatus.CREATED).body(entity);
    }
}
