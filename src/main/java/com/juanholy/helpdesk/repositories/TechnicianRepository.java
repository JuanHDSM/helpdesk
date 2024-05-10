package com.juanholy.helpdesk.repositories;

import com.juanholy.helpdesk.domain.Technician;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TechnicianRepository extends JpaRepository<Technician, Long> {
}
