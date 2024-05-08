package com.juanholy.helpdesk.domain.enums;

public enum Status {

    OPEN("open"),
    IN_PROGRESS("in_progress"),
    CLOSED("closed");

    private String status;

    Status(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

}
