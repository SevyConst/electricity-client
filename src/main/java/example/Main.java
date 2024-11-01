package example;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Main {
    private static final Logger logger = LoggerFactory.getLogger(Main.class);

    /// The purpose of these two strings is defining a path to the .properties file
    public static final String CLI_ARG_TEST_ENV = "test";
    public static final String CLI_ARG_PROD_ENV = "prod";

    public static final String PATH_TO_PROPERTIES_TEST_ENV =
            "/Users/konstantinlopatko/My Projects/Electricity-Client/properties/test.properties";
    public static final String PATH_TO_PROPERTIES_PROD_ENV =
            "";

    public static void main(String[] args) {
        if (args.length == 0) {
            logger.error("The command line argument is required ('"
                    + CLI_ARG_TEST_ENV  + "' or '" + CLI_ARG_PROD_ENV + "')");
            return;
        }

        String pathToProperties;
        switch (args[0]) {
            case CLI_ARG_TEST_ENV ->
                    pathToProperties = PATH_TO_PROPERTIES_TEST_ENV;
            case CLI_ARG_PROD_ENV ->
                    pathToProperties = PATH_TO_PROPERTIES_PROD_ENV;
            default -> {
                logger.error("The command line argument must be '" +
                        CLI_ARG_TEST_ENV  + "' or '" + CLI_ARG_PROD_ENV + "'");
                return;
            }
        }

        ReadingProperties readingProperties = new ReadingProperties();
        Params params;
        try {
            params = readingProperties.read(pathToProperties);
        } catch (ReadingPropertiesException e) {
            logger.error("can't read properties from {}", pathToProperties, e);
            return;
        }

        MainCycle mainCycle = new MainCycle(params);
        mainCycle.start();
    }
}