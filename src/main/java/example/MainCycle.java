package example;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MainCycle {
    private static final Logger logger =
            LoggerFactory.getLogger(MainCycle.class);

    private final Params params;

    public MainCycle(Params params) {
        this.params = params;
    }

    void start() {
        logger.info("Starting cycle...");

    }
}
