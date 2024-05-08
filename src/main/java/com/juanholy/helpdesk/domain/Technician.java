package com.juanholy.helpdesk.domain;

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
@Table
public class Technician extends User {


    @OneToMany(mappedBy = "technician")
    private List<Request> requests = new ArrayList<>();

    public Technician(Long id, String name, @CPF String cpf, String email, String password, Set<Profile> profile, LocalDate createDate, List<Request> requests) {
        super(id, name, cpf, email, password, profile, createDate);
        this.requests = requests;
    }
}
