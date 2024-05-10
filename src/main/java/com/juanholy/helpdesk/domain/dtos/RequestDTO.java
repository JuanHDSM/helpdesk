package com.juanholy.helpdesk.domain.dtos;

import com.juanholy.helpdesk.domain.enums.Priority;

public record RequestDTO(
        Priority priority,
        String title,
        String description,
        Long technician_id,
        Long client_id
) {
}
