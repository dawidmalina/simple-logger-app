package pl.malina.nasted;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SimpleException {

    private static Logger logger = LoggerFactory.getLogger(SimpleException.class);

    public static void printException() {
        logger.error("This is SimpleException", new NullPointerException("oops"));
    }
}
