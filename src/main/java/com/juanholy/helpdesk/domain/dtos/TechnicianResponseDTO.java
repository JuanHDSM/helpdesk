package com.juanholy.helpdesk.domain.dtos;

import com.juanholy.helpdesk.domain.Technician;
import com.juanholy.helpdesk.domain.enums.Profile;

import java.time.LocalDate;
import java.util.Set;

public record TechnicianResponseDTO(
        Long id,
        String name,
        String cpf,
        String email,
        String password,
        Set<Profile> profiles,
        LocalDate createDate
) {
    public static TechnicianResponseDTO fromTechnicianResponseDTO(Technician entity) {
        return new TechnicianResponseDTO(
                entity.getId(),
                entity.getName(),
                entity.getCpf(),
                entity.getEmail(),
                entity.getPassword(),
                entity.getProfiles(),
                entity.getCreateDate()
        );
    }
}
