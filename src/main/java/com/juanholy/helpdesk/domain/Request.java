package com.juanholy.helpdesk.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.juanholy.helpdesk.domain.dtos.RequestDTO;
import com.juanholy.helpdesk.domain.enums.Priority;
import com.juanholy.helpdesk.domain.enums.Status;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
@Entity
@Table
public class Request {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate createDate = LocalDate.now();
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate closedDate;
    private Priority priority;
    private Status status = Status.OPEN;
    private String title;
    private String description;
    @ManyToOne
    @JoinColumn(name = "technician_id")
    private Technician technician;
    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client client;

    public Request(Long id, LocalDate closedDate, Priority priority, Status status, String title, String description, Technician technician, Client client) {
        this.id = id;
        this.closedDate = closedDate;
        this.priority = priority;
        this.status = status;
        this.title = title;
        this.description = description;
        this.technician = technician;
        this.client = client;
    }

    public Request(RequestDTO data) {
        this.priority = data.priority();
        this.title = data.title();
        this.description = data.description();
    }
}
