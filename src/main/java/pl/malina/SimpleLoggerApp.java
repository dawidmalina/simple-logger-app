package pl.malina;

import java.util.Map;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.prometheus.client.exporter.MetricsServlet;
import io.prometheus.client.hotspot.DefaultExports;
import pl.malina.audit.SimpleAuditLogger;
import pl.malina.nasted.NastedLogger;

public class SimpleLoggerApp {

    private final static Logger logger = LoggerFactory.getLogger(SimpleLoggerApp.class);

    private static void generateLogs(Integer sleep) {
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

    public static void main(String[] args) throws Exception {

        Integer sleep = 1000;
        Map<String, String> env = System.getenv();
        if (env.containsKey("SLEEP")) {
            sleep = Integer.valueOf(env.get("SLEEP"));
        }

        DefaultExports.initialize();
        Server server = new Server(1080);
        ServletHandler handler = new ServletHandler();
        server.setHandler(handler);
        handler.addServletWithMapping(MetricsServlet.class, "/metrics");
        server.start();

        generateLogs(sleep);
    }

}
