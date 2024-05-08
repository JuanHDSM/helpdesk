package com.juanholy.helpdesk.domain.enums;

public enum Profile {

    ADMIN("admin"),
    CLIENT("client"),
    TECHNICIAN("technician");

    private String profile;

    Profile(String profile) {
        this.profile = profile;
    }

    public String getProfile() {
        return profile;
    }

}
