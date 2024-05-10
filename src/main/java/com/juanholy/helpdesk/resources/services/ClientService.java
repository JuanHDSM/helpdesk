package com.juanholy.helpdesk.resources.services;

import com.juanholy.helpdesk.domain.Client;
import com.juanholy.helpdesk.domain.Technician;
import com.juanholy.helpdesk.domain.dtos.ClientRequestDTO;
import com.juanholy.helpdesk.domain.dtos.ClientResponseDTO;
import com.juanholy.helpdesk.domain.dtos.TechnicianRequestDTO;
import com.juanholy.helpdesk.domain.dtos.TechnicianResponseDTO;
import com.juanholy.helpdesk.repositories.ClientRepository;
import com.juanholy.helpdesk.repositories.TechnicianRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class ClientService {

    @Autowired
    private ClientRepository repository;

    public List<ClientResponseDTO> findAll() {
        List<Client> list = repository.findAll();
        return list.stream().map(ClientResponseDTO::fromClientResponseDTO).toList();
    }

    public ClientResponseDTO findById(Long id) {
        Client entity = repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Cliente não encontrado."
                ));
        return ClientResponseDTO.fromClientResponseDTO(entity);
    }

    public ClientResponseDTO findByCpf(String cpf) {
        Client client = repository.findByCpf(cpf);
        return ClientResponseDTO.fromClientResponseDTO(client);
    }

    public ClientResponseDTO insert(ClientRequestDTO obj) {
        if (repository.findByCpf(obj.cpf()) != null){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "CPF já cadastrado.");
        }
        Client entity = repository.save(new Client(obj));
        return ClientResponseDTO.fromClientResponseDTO(entity);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }

    public ClientResponseDTO update(Long id, ClientRequestDTO objDTO) {
        Client obj = new Client(objDTO);
        Client entity = repository.findById(id).map(u -> {
            obj.setId(u.getId());
            repository.save(obj);
            return obj;
        }).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente não encontrado.", new Throwable("Cliente não encontrado")));
        return ClientResponseDTO.fromClientResponseDTO(entity);
    }
}
