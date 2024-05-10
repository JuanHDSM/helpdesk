package com.juanholy.helpdesk.resources.controllers;

import com.juanholy.helpdesk.domain.dtos.ClientRequestDTO;
import com.juanholy.helpdesk.domain.dtos.ClientResponseDTO;
import com.juanholy.helpdesk.repositories.ClientRepository;
import com.juanholy.helpdesk.resources.services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/clients")
public class ClientController {

    @Autowired
    private ClientService service;
    @Autowired
    private ClientRepository repository;

    @GetMapping
    public ResponseEntity<List<ClientResponseDTO>> findAll() {
        List<ClientResponseDTO> list = service.findAll();
        return ResponseEntity.ok(list);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClientResponseDTO> findById(@PathVariable Long id) {
        ClientResponseDTO obj = service.findById(id);
        return ResponseEntity.ok(obj);
    }

    @GetMapping("/cpfSearch")
    public ResponseEntity<ClientResponseDTO> findByCpf(@RequestParam("cpf") String cpf) {
        ClientResponseDTO obj = service.findByCpf(cpf);
        return ResponseEntity.ok(obj);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<ClientResponseDTO> insert(@RequestBody ClientRequestDTO obj) {
        ClientResponseDTO entity = service.insert(obj);
        return ResponseEntity.status(HttpStatus.CREATED).body(entity);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ClientResponseDTO> update(@PathVariable Long id, @RequestBody ClientRequestDTO obj) {
        ClientResponseDTO entity = service.update(id, obj);
        return ResponseEntity.ok(entity);
    }
}
