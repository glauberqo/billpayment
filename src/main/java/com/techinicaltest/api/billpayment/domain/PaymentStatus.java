package com.techinicaltest.api.billpayment.domain;

public enum PaymentStatus {
    OPENED("Opened"),
    PAYED("Payed");

    private String description;

    PaymentStatus(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
