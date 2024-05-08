package com.juanholy.helpdesk.domain;

import com.juanholy.helpdesk.domain.enums.Priority;
import com.juanholy.helpdesk.domain.enums.Status;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Request {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDate createDate = LocalDate.now();
    private LocalDate closedDate;
    private Priority priority;
    private Status status;
    private String title;
    private String description;
    private Technician technician;
    private Client client;
}
