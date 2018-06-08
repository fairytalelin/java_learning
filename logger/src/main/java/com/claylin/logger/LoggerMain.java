package com.claylin.logger;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LoggerMain {
    private static final Logger logger = LoggerFactory.getLogger(LoggerMain.class);

    public static void main(String[] args) {
        String method = "LoggerMain::main";
        long start = System.currentTimeMillis();
        try {
            logger.debug("{}", method);
            logger.info("{}", method);
            logger.warn("{}", method);
            logger.error("{}", method);
        } finally {
            logger.info("{}, cost: {}ms", method, System.currentTimeMillis() - start);
        }
    }
}
