package com.juanholy.helpdesk.repositories;

import com.juanholy.helpdesk.domain.Request;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RequestRepository extends JpaRepository<Request, Long> {
}
