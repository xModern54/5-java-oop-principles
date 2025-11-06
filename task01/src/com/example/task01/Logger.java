package com.example.task01;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

/**
 * Простой логгер с форматированием сообщений и фильтром по уровню.
 */
public class Logger {

    private static final Map<String, Logger> LOGGERS = new HashMap<>();
    private static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("yyyy.MM.dd HH:mm:ss");

    private final String name;
    private Level level = Level.DEBUG;

    private Logger(String name) {
        this.name = name;
    }

    public static synchronized Logger getLogger(String name) {
        Logger logger = LOGGERS.get(name);
        if (logger == null) {
            logger = new Logger(name);
            LOGGERS.put(name, logger);
        }
        return logger;
    }

    public String getName() {
        return name;
    }

    public Level getLevel() {
        return level;
    }

    public void setLevel(Level level) {
        if (level == null) {
            throw new IllegalArgumentException("Level must not be null");
        }
        this.level = level;
    }

    public void log(Level level, String message) {
        if (level == null || message == null) {
            throw new IllegalArgumentException("Level and message must not be null");
        }
        if (level.ordinal() < this.level.ordinal()) {
            return;
        }
        LocalDateTime now = LocalDateTime.now();
        String dateTime = DATE_FORMAT.format(now);
        int splitIndex = dateTime.indexOf(' ');
        String date = dateTime.substring(0, splitIndex);
        String time = dateTime.substring(splitIndex + 1);
        System.out.printf("[%s] %s %s %s - %s%n", level.name(), date, time, name, message);
    }

    public void log(Level level, String pattern, Object... args) {
        if (pattern == null) {
            throw new IllegalArgumentException("Pattern must not be null");
        }
        log(level, String.format(pattern, args));
    }

    public void debug(String message) {
        log(Level.DEBUG, message);
    }

    public void debug(String pattern, Object... args) {
        log(Level.DEBUG, pattern, args);
    }

    public void info(String message) {
        log(Level.INFO, message);
    }

    public void info(String pattern, Object... args) {
        log(Level.INFO, pattern, args);
    }

    public void warning(String message) {
        log(Level.WARNING, message);
    }

    public void warning(String pattern, Object... args) {
        log(Level.WARNING, pattern, args);
    }

    public void error(String message) {
        log(Level.ERROR, message);
    }

    public void error(String pattern, Object... args) {
        log(Level.ERROR, pattern, args);
    }

    public enum Level {
        DEBUG,
        INFO,
        WARNING,
        ERROR
    }
}
