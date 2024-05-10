package com.juanholy.helpdesk.repositories;

import com.juanholy.helpdesk.domain.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client, Long> {

}
