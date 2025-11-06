package com.example.task04;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class FileHandler implements MessageHandler {

    private final Path file;

    public FileHandler(Path file) {
        this.file = file;
    }

    @Override
    public void publish(LogRecord record) {
        try {
            Path parent = file.getParent();
            if (parent != null) {
                Files.createDirectories(parent);
            }
            try (FileWriter writer = new FileWriter(file.toFile(), true)) {
                writer.write(record.format());
                writer.write(System.lineSeparator());
            }
        } catch (IOException e) {
            throw new RuntimeException("Unable to write log file", e);
        }
    }
}
