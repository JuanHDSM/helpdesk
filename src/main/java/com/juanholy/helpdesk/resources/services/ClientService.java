package com.juanholy.helpdesk.resources.services;

import com.juanholy.helpdesk.domain.Client;
import com.juanholy.helpdesk.domain.User;
import com.juanholy.helpdesk.domain.dtos.ClientRequestDTO;
import com.juanholy.helpdesk.domain.dtos.ClientResponseDTO;
import com.juanholy.helpdesk.repositories.ClientRepository;
import com.juanholy.helpdesk.repositories.UserRepository;
import com.juanholy.helpdesk.resources.services.exceptions.DataIntegrityViolationException;
import com.juanholy.helpdesk.resources.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientService {

    @Autowired
    private ClientRepository repository;
    @Autowired
    private UserRepository userRepository;

    public List<ClientResponseDTO> findAll() {
        List<Client> list = repository.findAll();
        return list.stream().map(ClientResponseDTO::fromClientResponseDTO).toList();
    }

    public ClientResponseDTO findById(Long id) {
        Client entity = repository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException("Cliente não encontrado."));
        return ClientResponseDTO.fromClientResponseDTO(entity);
    }

    public ClientResponseDTO findByCpf(String cpf) {
        User user = userRepository.findByCpf(cpf);
        return ClientResponseDTO.fromUserResponseDTO(user);
    }

    public ClientResponseDTO insert(ClientRequestDTO objDTO) {
        Client obj = new Client(objDTO);
        ValidCpfAndEmail(objDTO);
        repository.save(obj);
        return ClientResponseDTO.fromClientResponseDTO(obj);
    }

    public UserDetails loadByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByLogin(username);
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
        }).orElseThrow(() -> new ObjectNotFoundException("Cliente não encontrado."));
        return ClientResponseDTO.fromClientResponseDTO(entity);
    }

    private void ValidCpfAndEmail(ClientRequestDTO objDTO) {
        if (userRepository.findByCpf(objDTO.cpf()) != null){
            throw new DataIntegrityViolationException("CPF já cadastrado.");
        }
        if (userRepository.findByEmail(objDTO.email()) != null) {
            throw new DataIntegrityViolationException("E-mail já cadastrado.");
        }
    }
}
