package com.juanholy.helpdesk.resources.controllers;

import com.juanholy.helpdesk.domain.dtos.TechnicianRequestDTO;
import com.juanholy.helpdesk.domain.dtos.TechnicianResponseDTO;
import com.juanholy.helpdesk.resources.services.TechnicianService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/technicians")
public class TechnicianController {

    @Autowired
    private TechnicianService service;

    @GetMapping
    public List<TechnicianResponseDTO> findAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public TechnicianResponseDTO findById(@PathVariable Long id) {
        return service.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public TechnicianResponseDTO insert(@RequestBody TechnicianRequestDTO obj) {
        return service.insert(obj);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }

    @PutMapping("/{id}")
    public TechnicianResponseDTO update(@PathVariable Long id, @RequestBody TechnicianRequestDTO obj) {
        return service.update(id, obj);
    }
}
