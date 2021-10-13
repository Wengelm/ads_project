package com.senla.ads.entity;

public enum AdStatus  {


    NEW("NEW"),
    COMPLETED("COMPLETED");


    private final String name;

    private AdStatus(String s) {
        name = s;
    }


    public String toString() {
        return this.name;
    }
}
