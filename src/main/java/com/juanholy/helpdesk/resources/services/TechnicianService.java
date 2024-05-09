package com.juanholy.helpdesk.resources.services;

import com.juanholy.helpdesk.domain.Technician;
import com.juanholy.helpdesk.domain.dtos.TechnicianRequestDTO;
import com.juanholy.helpdesk.domain.dtos.TechnicianResponseDTO;
import com.juanholy.helpdesk.repositories.TechnicianRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Set;

@Service
public class TechnicianService {

    @Autowired
    private TechnicianRepository repository;

    public List<TechnicianResponseDTO> findAll() {
        List<Technician> list = repository.findAll();
        return list.stream().map(TechnicianResponseDTO::fromTechnicianResponseDTO).toList();
    }

    public TechnicianResponseDTO findById(Long id) {
        Technician entity = repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Técnico não encontrado."
                ));
        return TechnicianResponseDTO.fromTechnicianResponseDTO(entity);
    }

    public TechnicianResponseDTO insert(TechnicianRequestDTO obj) {
        Technician entity = repository.save(new Technician(obj));
        return TechnicianResponseDTO.fromTechnicianResponseDTO(entity);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }

    public TechnicianResponseDTO update(Long id, TechnicianRequestDTO objDTO) {
        Technician obj = new Technician(objDTO);
        Technician entity = repository.findById(id).map(u -> {
            obj.setId(u.getId());
            repository.save(obj);
            return obj;
        }).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Técnico não encontrado."));
        return TechnicianResponseDTO.fromTechnicianResponseDTO(entity);
    }
}
