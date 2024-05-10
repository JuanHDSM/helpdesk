package com.juanholy.helpdesk.domain.dtos;

import com.juanholy.helpdesk.domain.Client;
import com.juanholy.helpdesk.domain.Request;
import com.juanholy.helpdesk.domain.Technician;
import com.juanholy.helpdesk.domain.enums.Priority;
import com.juanholy.helpdesk.domain.enums.Status;
import com.juanholy.helpdesk.repositories.RequestRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;

public record RequestResponseDTO(
        Long id,
        LocalDate createDate,
        LocalDate closedDate,
        Priority priority,
        Status status,
        String title,
        String description,
        Technician technician,
        Client client
) {
    public static RequestResponseDTO fromRequestResponseDTO(Request entity) {
        return new RequestResponseDTO(
                entity.getId(),
                entity.getCreateDate(),
                entity.getClosedDate(),
                entity.getPriority(),
                entity.getStatus(),
                entity.getTitle(),
                entity.getDescription(),
                entity.getTechnician(),
                entity.getClient()
        );
    }
}
