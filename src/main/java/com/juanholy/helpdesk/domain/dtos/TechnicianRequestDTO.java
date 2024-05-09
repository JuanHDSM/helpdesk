package com.juanholy.helpdesk.domain.dtos;

import com.juanholy.helpdesk.domain.enums.Profile;

public record TechnicianRequestDTO(
        String name,
        String cpf,
        String email,
        String password,
        Profile profile
) {}
