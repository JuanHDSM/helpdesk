package com.juanholy.helpdesk.repositories;

import com.juanholy.helpdesk.domain.Client;
import com.juanholy.helpdesk.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
