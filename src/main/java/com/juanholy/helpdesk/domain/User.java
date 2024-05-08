package com.juanholy.helpdesk.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.juanholy.helpdesk.domain.enums.Profile;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.br.CPF;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@Entity(name = "users")
@Table(name = "users")
public abstract class User {

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
    @Enumerated(EnumType.STRING)
    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "PROFILES")
    protected Set<Profile> profile = new HashSet<>();

    @JsonFormat(pattern = "dd//MM//yyyy")
    protected LocalDate createDate = LocalDate.now();

    public User() {
        super();
        this.profile.add(Profile.CLIENT);
    }

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

    public User(Long id, String name, String cpf, String email, String password) {
        this.id = id;
        this.name = name;
        this.cpf = cpf;
        this.email = email;
        this.password = password;
        this.profile.add(Profile.CLIENT);
    }

    public void addProfile(Profile profile) {
        this.profile.add(profile);
    }
}
