package com.juanholy.helpdesk.resources.services;

import com.juanholy.helpdesk.domain.Client;
import com.juanholy.helpdesk.domain.Request;
import com.juanholy.helpdesk.domain.Technician;
import com.juanholy.helpdesk.domain.dtos.RequestDTO;
import com.juanholy.helpdesk.domain.dtos.RequestResponseDTO;
import com.juanholy.helpdesk.repositories.ClientRepository;
import com.juanholy.helpdesk.repositories.RequestRepository;
import com.juanholy.helpdesk.repositories.TechnicianRepository;
import com.juanholy.helpdesk.resources.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RequestService {
    @Autowired
    private RequestRepository repository;
    @Autowired
    TechnicianRepository technicianRepository;

    @Autowired
    ClientRepository clientRepository;

    public List<RequestResponseDTO> findAll() {
        List<Request> list = repository.findAll();
        return list.stream().map(RequestResponseDTO::fromRequestResponseDTO).toList();
    }

    public RequestResponseDTO findById(Long id) {
        Request obj = repository.findById(id)
                .orElseThrow( () -> new ObjectNotFoundException(
                        "Chamado não encontrado."
                ));
        return RequestResponseDTO.fromRequestResponseDTO(obj);
    }

    public RequestResponseDTO insert(RequestDTO obj) {
        Technician tec = technicianRepository.findById(obj.technician_id()).orElseThrow(() -> new ObjectNotFoundException("Técnico não encotrado."));
        Client cli = clientRepository.findById(obj.client_id()).orElseThrow(() -> new ObjectNotFoundException("Cliente não encotrado."));
        Request request = new Request(obj);
        request.setTechnician(tec);
        request.setClient(cli);
        repository.save(request);
        return RequestResponseDTO.fromRequestResponseDTO(request);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }

    public RequestResponseDTO update(Long id, RequestDTO objDTO) {
        Request request = new Request(objDTO);
        Request entity = repository.findById(id).map(
                u -> {
                    request.setId(u.getId());
                    return repository.save(request);
                }
        ).orElseThrow(
                () ->
                        new ObjectNotFoundException(
                                "Chamado não encontrado."
                        )
        );
        return RequestResponseDTO.fromRequestResponseDTO(entity);
    }
}
