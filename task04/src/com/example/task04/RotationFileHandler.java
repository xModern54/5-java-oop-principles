package com.example.task04;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class RotationFileHandler implements MessageHandler {

    private final Path directory;
    private final String prefix;
    private final ChronoUnit rotationUnit;

    public RotationFileHandler(Path directory, String prefix, ChronoUnit rotationUnit) {
        this.directory = directory;
        this.prefix = prefix;
        this.rotationUnit = rotationUnit;
    }

    @Override
    public void publish(LogRecord record) {
        LocalDateTime time = record.getTimestamp();
        LocalDateTime truncated = truncate(time);
        DateTimeFormatter formatter = formatter();
        String suffix = truncated.format(formatter);
        Path file = directory.resolve(prefix + "_" + suffix + ".log");
        write(file, record.format());
    }

    private void write(Path file, String message) {
        try {
            Path parent = file.getParent();
            if (parent != null) {
                Files.createDirectories(parent);
            }
            try (FileWriter writer = new FileWriter(file.toFile(), true)) {
                writer.write(message);
                writer.write(System.lineSeparator());
            }
        } catch (IOException e) {
            throw new RuntimeException("Unable to write rotated log", e);
        }
    }

    private LocalDateTime truncate(LocalDateTime time) {
        switch (rotationUnit) {
            case MINUTES:
                return time.withSecond(0).withNano(0);
            case HOURS:
                return time.withMinute(0).withSecond(0).withNano(0);
            case DAYS:
                return time.withHour(0).withMinute(0).withSecond(0).withNano(0);
            default:
                throw new IllegalArgumentException("Unsupported rotation unit: " + rotationUnit);
        }
    }

    private DateTimeFormatter formatter() {
        switch (rotationUnit) {
            case MINUTES:
                return DateTimeFormatter.ofPattern("yyyyMMdd_HHmm");
            case HOURS:
                return DateTimeFormatter.ofPattern("yyyyMMdd_HH");
            case DAYS:
                return DateTimeFormatter.ofPattern("yyyyMMdd");
            default:
                throw new IllegalArgumentException("Unsupported rotation unit: " + rotationUnit);
        }
    }
}
