package com.gtsystems.bakerysystem;

import com.gtsystems.bakerysystem.persistence.AccountsPersistanceHandler;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        try {
            AccountsPersistanceHandler.addSale(
                    "João",
                    230.0
            );
        } catch(IOException e) { System.out.println("WARNING: Internal gson error"); }
    }
}
