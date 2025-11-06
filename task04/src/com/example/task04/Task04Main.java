package com.example.task04;

import java.nio.file.Paths;
import java.time.temporal.ChronoUnit;

public class Task04Main {
    public static void main(String[] args) {
        Logger logger = Logger.getLogger("demo");
        logger.clearHandlers();
        ConsoleHandler console = new ConsoleHandler();
        logger.addHandler(console);
        logger.addHandler(new FileHandler(Paths.get("logs", "demo.log")));
        MemoryHandler memory = new MemoryHandler(new RotationFileHandler(Paths.get("logs"), "buffer", ChronoUnit.HOURS), 2);
        logger.addHandler(memory);

        logger.info("Application started");
        logger.warning("Args received: %d", args.length);
        logger.error("Something went wrong");
        memory.flush();
    }
}
