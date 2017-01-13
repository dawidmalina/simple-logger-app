package pl.malina;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import pl.malina.audit.SimpleAuditLogger;
import pl.malina.nasted.NastedLogger;

public class SimpleLoggerApp {

    private final static Logger logger = LoggerFactory.getLogger(SimpleLoggerApp.class);

    public static void main(String[] args) {

        Integer sleep = 1000;
        Map<String, String> env = System.getenv();
        if (env.containsKey("SLEEP")) {
            sleep = Integer.valueOf(env.get("SLEEP"));
        }

        while (true) {
            try {

                Thread.sleep(sleep);
                final int randomNum = (int) (Math.random() * 100);

                // 40%
                if (randomNum < 60) {
                    logger.info("This is nice info");
                    // 30%
                } else if (randomNum >= 60 && randomNum < 80) {
                    logger.warn("This is wird waring");
                    SimpleAuditLogger.printAuditLog();
                    // 20%
                } else if (randomNum >= 80 && randomNum < 90) {
                    logger.error("This is nasty errors");
                    // 10%
                } else if (randomNum >= 90) {
                    NastedLogger.printStack();
                }

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

}
