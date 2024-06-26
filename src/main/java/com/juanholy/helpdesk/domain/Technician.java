package com.juanholy.helpdesk.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.juanholy.helpdesk.domain.dtos.TechnicianRequestDTO;
import com.juanholy.helpdesk.domain.enums.Profile;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.*;
import org.hibernate.validator.constraints.br.CPF;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Technician extends User {


    @OneToMany(mappedBy = "technician")
    @JsonIgnore
    private List<Request> requests = new ArrayList<>();

    public Technician(Long id, String name, @CPF String cpf, String email, String password, Set<Profile> profiles, LocalDate createDate, List<Request> requests) {
        super(id, name, cpf, email, password, profiles, createDate);
        this.requests = requests;
    }

    public Technician(Long id, String name, String cpf, String email, String password, Profile profile) {
        super(id, name, cpf, email, password, profile);
    }

    public Technician(TechnicianRequestDTO technicianRequestDTO) {
        super(technicianRequestDTO);
    }
}
