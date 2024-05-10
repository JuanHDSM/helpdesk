package com.juanholy.helpdesk.resources.services;

import com.juanholy.helpdesk.domain.Technician;
import com.juanholy.helpdesk.domain.dtos.ClientRequestDTO;
import com.juanholy.helpdesk.domain.dtos.TechnicianRequestDTO;
import com.juanholy.helpdesk.domain.dtos.TechnicianResponseDTO;
import com.juanholy.helpdesk.repositories.TechnicianRepository;
import com.juanholy.helpdesk.repositories.UserRepository;
import com.juanholy.helpdesk.resources.services.exceptions.DataIntegrityViolationException;
import com.juanholy.helpdesk.resources.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TechnicianService {

    @Autowired
    private TechnicianRepository repository;
    @Autowired
    private UserRepository userRepository;

    public List<TechnicianResponseDTO> findAll() {
        List<Technician> list = repository.findAll();
        return list.stream().map(TechnicianResponseDTO::fromTechnicianResponseDTO).toList();
    }

    public TechnicianResponseDTO findById(Long id) {
        Technician entity = repository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException(
                        "Técnico não encontrado."
                ));
        return TechnicianResponseDTO.fromTechnicianResponseDTO(entity);
    }

    public TechnicianResponseDTO insert(TechnicianRequestDTO objDTO) {
        Technician obj = new Technician(objDTO);
        ValidCpfAndEmail(objDTO);
        repository.save(obj);
        return TechnicianResponseDTO.fromTechnicianResponseDTO(obj);
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
        }).orElseThrow(() -> new ObjectNotFoundException("Técnico não encontrado."));
        return TechnicianResponseDTO.fromTechnicianResponseDTO(entity);
    }

    private void ValidCpfAndEmail(TechnicianRequestDTO objDTO) {
        if (userRepository.findByCpf(objDTO.cpf()) != null){
            throw new DataIntegrityViolationException("CPF já cadastrado.");
        }
        if (userRepository.findByEmail(objDTO.email()) != null) {
            throw new DataIntegrityViolationException("E-mail já cadastrado.");
        }
    }
}
