package com.fnag;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

/**
 * Entry point of the application
 *
 * @author Etienne Peiniau <etienne.peiniau@gmail.com>
 */
@SpringBootApplication
public class FnagResultAnalyser {

    public static final Logger logger = LoggerFactory.getLogger(FnagResultAnalyser.class);

    public static void main(String[] args) {
        try {
            // Run the application
            new SpringApplicationBuilder()
                    .showBanner(false)
                    .profiles("runner")
                    .sources(FnagResultAnalyser.class)
                    .run(args);
        } catch (RuntimeException re) {
            logger.error("An error occured please check the log file : fnag.log");
        }
    }

}
