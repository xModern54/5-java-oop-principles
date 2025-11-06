package com.example.task04;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class LogRecord {

    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy.MM.dd HH:mm:ss");

    private final LocalDateTime timestamp;
    private final Logger.Level level;
    private final String loggerName;
    private final String message;

    public LogRecord(LocalDateTime timestamp, Logger.Level level, String loggerName, String message) {
        this.timestamp = timestamp;
        this.level = level;
        this.loggerName = loggerName;
        this.message = message;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public Logger.Level getLevel() {
        return level;
    }

    public String getLoggerName() {
        return loggerName;
    }

    public String getMessage() {
        return message;
    }

    public String format() {
        String dateTime = FORMATTER.format(timestamp);
        int spaceIndex = dateTime.indexOf(' ');
        String date = dateTime.substring(0, spaceIndex);
        String time = dateTime.substring(spaceIndex + 1);
        return String.format("[%s] %s %s %s - %s", level.name(), date, time, loggerName, message);
    }
}
