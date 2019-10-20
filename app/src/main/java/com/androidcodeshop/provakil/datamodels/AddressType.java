package com.androidcodeshop.provakil.datamodels;

public class AddressType {
    private int id;
    private String value;

    public AddressType(int id, String value) {
        this.id = id;
        this.value = value;
    }

    public int getId() {
        return id;
    }

    public String getValue() {
        return value;
    }
}
