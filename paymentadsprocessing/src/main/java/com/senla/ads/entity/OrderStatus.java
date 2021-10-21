package com.senla.ads.entity;

public enum OrderStatus {
    NEW("NEW"),
    PAID("PAID"),
    WAIT_PAID("WAINTING PAID");



    private final String name;

    private OrderStatus(String s) {
        name = s;
    }


    public String toString() {
        return this.name;
    }
}
