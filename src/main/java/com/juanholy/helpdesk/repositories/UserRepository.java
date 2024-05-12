package com.juanholy.helpdesk.repositories;

import com.juanholy.helpdesk.domain.Client;
import com.juanholy.helpdesk.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByCpf(String cpf);
    User findByEmail(String email);
    UserDetails findByLogin(String login);
}
