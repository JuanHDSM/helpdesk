package com.juanholy.helpdesk.domain;

import com.juanholy.helpdesk.domain.enums.Profile;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.validator.constraints.br.CPF;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public abstract class User {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;
    protected String name;
    @CPF
    protected String cpf;
    protected String email;
    protected String password;
    @Enumerated(EnumType.STRING)
    protected Set<Profile> profile = new HashSet<>();
    protected LocalDate createDate = LocalDate.now();

    public User(Long id, String name, String cpf, String email, String password, LocalDate createDate) {
        super();
        this.id = id;
        this.name = name;
        this.cpf = cpf;
        this.email = email;
        this.password = password;
        this.createDate = createDate;
        this.profile.add(Profile.CLIENT);
    }
}
