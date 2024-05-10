package com.juanholy.helpdesk.repositories;

import com.juanholy.helpdesk.domain.Client;
import com.juanholy.helpdesk.domain.Request;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client, Long> {

    Client findByCpf(String cpf);
}
