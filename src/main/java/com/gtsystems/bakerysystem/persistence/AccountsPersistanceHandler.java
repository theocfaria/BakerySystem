package com.gtsystems.bakerysystem.persistence;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import com.gtsystems.bakerysystem.util.LocalTimeAdapter;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

public class AccountsPersistanceHandler {
    public static final Gson gson = new GsonBuilder()
            .setPrettyPrinting()
            .registerTypeAdapter(LocalDateTime.class, new LocalTimeAdapter())
            .create();
    public static final String filePath = "data" + File.separator + "accounts.json";

    public static Map<String, Double> loadData() throws IOException {
        File file = new File(filePath);
        if (!file.exists() || file.length() == 0) {
            return new HashMap<>();
        }

        try (FileReader reader = new FileReader(filePath)) {
            Type mapType = new TypeToken<Map<String, Double>>(){}.getType();
            Map<String, Double> accounts = gson.fromJson(reader, mapType);
            return accounts != null ? accounts : new HashMap<>();
        }
    }

    public static void saveData(Map<String, Double> accounts) throws IOException {
        try (FileWriter writer = new FileWriter(filePath)) {
            gson.toJson(accounts, writer);
        }
    }

    public static void addSale(String clientName, Double newSaleTotal) throws IOException {
        Map<String, Double> currentData = loadData();
        currentData.remove(clientName);
        currentData.put(clientName, newSaleTotal);
        saveData(currentData);
    }
}
