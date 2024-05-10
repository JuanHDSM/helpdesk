package com.juanholy.helpdesk.domain.dtos;

import com.juanholy.helpdesk.domain.Client;
import com.juanholy.helpdesk.domain.Technician;
import com.juanholy.helpdesk.domain.User;
import com.juanholy.helpdesk.domain.enums.Profile;

import java.time.LocalDate;
import java.util.Set;

public record ClientResponseDTO(
        Long id,
        String name,
        String cpf,
        String email,
        String password,
        Set<Profile> profiles,
        LocalDate createDate
) {
    public static ClientResponseDTO fromClientResponseDTO(Client entity) {
        return new ClientResponseDTO(
                entity.getId(),
                entity.getName(),
                entity.getCpf(),
                entity.getEmail(),
                entity.getPassword(),
                entity.getProfiles(),
                entity.getCreateDate()
        );
    }

    public static ClientResponseDTO fromUserResponseDTO(User entity) {
        return new ClientResponseDTO(
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
