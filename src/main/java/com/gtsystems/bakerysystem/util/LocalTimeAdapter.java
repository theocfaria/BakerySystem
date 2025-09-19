package com.gtsystems.bakerysystem.util;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class LocalTimeAdapter extends TypeAdapter<LocalDateTime> {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");

    @Override
    public void write(JsonWriter out, LocalDateTime value) throws IOException {
        if (value == null) {
            out.nullValue();
        } else {
            out.value(value.format(FORMATTER));
        }
    }

    @Override
    public LocalDateTime read(JsonReader in) throws IOException {
        String json = in.nextString();
        if (json == null || json.isEmpty()) {
            return null;
        }
        return LocalDateTime.parse(json, FORMATTER);
    }
}
