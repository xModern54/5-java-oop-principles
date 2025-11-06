package com.example.task04;

import java.util.ArrayList;
import java.util.List;

public class MemoryHandler implements MessageHandler {

    private final MessageHandler target;
    private final int limit;
    private final List<LogRecord> buffer = new ArrayList<>();

    public MemoryHandler(MessageHandler target, int limit) {
        if (target == null) {
            throw new IllegalArgumentException("Target handler must not be null");
        }
        if (limit < 0) {
            throw new IllegalArgumentException("Limit must be zero or positive");
        }
        this.target = target;
        this.limit = limit;
    }

    @Override
    public void publish(LogRecord record) {
        buffer.add(record);
        if (limit > 0 && buffer.size() >= limit) {
            flush();
        }
    }

    public void flush() {
        for (LogRecord record : buffer) {
            target.publish(record);
        }
        buffer.clear();
    }

    public void reset() {
        buffer.clear();
    }
}
