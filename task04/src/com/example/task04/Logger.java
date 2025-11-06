package com.example.task04;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Logger {

    private static final Map<String, Logger> LOGGERS = new HashMap<>();

    private final String name;
    private final List<MessageHandler> handlers = new ArrayList<>();
    private Level level = Level.DEBUG;

    private Logger(String name) {
        this.name = name;
        handlers.add(new ConsoleHandler());
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

    public void addHandler(MessageHandler handler) {
        if (handler == null) {
            throw new IllegalArgumentException("Handler must not be null");
        }
        handlers.add(handler);
    }

    public void removeHandler(MessageHandler handler) {
        handlers.remove(handler);
    }

    public void clearHandlers() {
        handlers.clear();
    }

    public void log(Level level, String message) {
        if (level == null || message == null) {
            throw new IllegalArgumentException("Level and message must not be null");
        }
        if (level.ordinal() < this.level.ordinal()) {
            return;
        }
        LogRecord record = new LogRecord(LocalDateTime.now(), level, name, message);
        for (MessageHandler handler : handlers) {
            handler.publish(record);
        }
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
