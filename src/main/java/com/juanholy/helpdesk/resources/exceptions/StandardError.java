package com.juanholy.helpdesk.resources.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class StandardError {
    private LocalDateTime timestamp;
    private Integer status;
    private String error;
    private String message;
    private String path;

}
