package pl.malina.audit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SimpleAuditLogger {

    private static Logger logger = LoggerFactory.getLogger(SimpleAuditLogger.class);

    public static void printAuditLog() {
        logger.info("This is very important audit log");
    }
}
