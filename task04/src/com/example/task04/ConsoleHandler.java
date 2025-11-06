package com.example.task04;

public class ConsoleHandler implements MessageHandler {

    @Override
    public void publish(LogRecord record) {
        System.out.println(record.format());
    }
}
