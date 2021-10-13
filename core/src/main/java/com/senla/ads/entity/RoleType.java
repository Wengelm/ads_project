package com.senla.ads.entity;

public enum RoleType {
    USER ("USER"),
    ADMIN ("ADMIN");


    private final String name;

    private RoleType(String s) {
        name = s;
    }


    public String toString() {
        return this.name;
    }

}
