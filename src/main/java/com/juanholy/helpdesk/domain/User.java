package com.juanholy.helpdesk.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.juanholy.helpdesk.domain.dtos.ClientRequestDTO;
import com.juanholy.helpdesk.domain.dtos.TechnicianRequestDTO;
import com.juanholy.helpdesk.domain.enums.Profile;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.validator.constraints.br.CPF;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Getter
@Setter
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@Entity(name = "users")
@Table(name = "users")
public abstract class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;
    protected String name;
    @CPF
    @Column(unique = true)
    protected String cpf;
    @Column(unique = true)
    protected String email;
    protected String password;
    @CollectionTable(name = "PROFILES")
    @Enumerated(EnumType.STRING)
    @ElementCollection(fetch = FetchType.EAGER)
    @Setter(AccessLevel.NONE)
    protected Set<Profile> profiles = new HashSet<>();

    @JsonFormat(pattern = "dd/MM/yyyy")
    protected LocalDate createDate = LocalDate.now();

    public User() {
        super();
    }

    public User(Long id, String name, String cpf, String email, String password, LocalDate createDate) {
        super();
        this.id = id;
        this.name = name;
        this.cpf = cpf;
        this.email = email;
        this.password = password;
        this.createDate = createDate;
    }

    public User(Long id, String name, String cpf, String email, String password, Profile profile) {
        this.id = id;
        this.name = name;
        this.cpf = cpf;
        this.email = email;
        this.password = password;
        this.addProfiles(Profile.CLIENT);
        this.addProfiles(profile);
    }

    public User(TechnicianRequestDTO data) {
        this.name = data.name();
        this.cpf = data.cpf();
        this.email = data.email();
        this.password = data.password();
        this.profiles.add(Profile.CLIENT);
        this.profiles.add(data.profile());
    }

    public User(ClientRequestDTO data) {
        this.name = data.name();
        this.cpf = data.cpf();
        this.email = data.email();
        this.password = data.password();
        this.profiles.add(Profile.CLIENT);
    }

    private void addProfiles(Profile profile) {
        this.profiles.add(profile);
    }
}
