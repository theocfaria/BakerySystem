package com.gtsystems.bakerysystem.model;

public class SaleModel {
    private String clientName;
    private final double total;

    public SaleModel() {
        clientName = "null";
        total = 0.0;
    }

    public SaleModel(String name, double total) {
        this.clientName = name;
        this.total = total;
    }

    public double getTotal() {return total;}

    public String getClientName() {return clientName;}

    public void setClientName(String clientName) {this.clientName = clientName;}
}
