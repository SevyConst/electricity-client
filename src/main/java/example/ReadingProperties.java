package example;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;
import java.util.Properties;

public class ReadingProperties {
    private static final Logger logger =
            LoggerFactory.getLogger(ReadingProperties.class);

    private static final String PROPERTY_IP_MAIN_SERVER = "ip_main_server";
    private static final String PROPERTY_PORT_MAIN_SERVER = "port_main_server";
    private static final String PROPERTY_IP_SECOND_SERVER = "ip_second_server";
    private static final String PROPERTY_PORT_SECOND_SERVER =
            "port_second_server";
    private static final String PROPERTY_PAUSE_BETWEEN_REQUESTS =
            "pause_between_requests_seconds";

    private static final String PROPERTY_DEVICE_NAME = "device_name";
    private static final String PROPERTY_AUTONOMOUS_CLOCK = "autonomous_clock";

    Params read(String pathToProperties) throws ReadingPropertiesException {
        Properties properties = new Properties();
        try (InputStream inputStream = new FileInputStream(pathToProperties)) {
            properties.load(inputStream);
        } catch (IOException e) {
            throw new ReadingPropertiesException("can't load properties");
        }

        Params params = new Params();
        readSendingProperties(properties, params);
        readDeviceProperties(properties, params);
        return params;
    }

    private void readSendingProperties(Properties properties, Params params)
            throws ReadingPropertiesException {
        params.setIpMainServer(readIpMainServer(properties));
        params.setPortMainServer(readPortMainServer(properties));
        
        params.setIpSecondServer(readIpSecondServer(properties));
        params.setPortSecondServer(readPortSecondServer(properties));

        if (Objects.isNull(params.getIpSecondServer())
                &&
                Objects.nonNull(params.getPortSecondServer())) {
            throw new ReadingPropertiesException("Property '" +
                    PROPERTY_IP_SECOND_SERVER +
                    "' isn't defined but property '" +
                    PROPERTY_PORT_SECOND_SERVER + "' is defined");
        }

        if (Objects.nonNull(params.getIpSecondServer())
                &&
                Objects.isNull(params.getPortSecondServer())) {
            throw new ReadingPropertiesException("Property '" +
                    PROPERTY_PORT_SECOND_SERVER +
                    "' isn't defined but property '" +
                    PROPERTY_IP_SECOND_SERVER + "' is defined");
        }


        params.pauseBetweenRequests(readPauseBetweenRequests(properties));
    }

    private String readIpMainServer(Properties properties)
            throws ReadingPropertiesException {
        String ip = properties.getProperty(PROPERTY_IP_MAIN_SERVER);
        if (Objects.isNull(ip) || ip.isEmpty()) {
                throw new ReadingPropertiesException("Property '" +
                        PROPERTY_IP_MAIN_SERVER + "' isn't defined");
        }

        logger.info("{}: {}", PROPERTY_IP_MAIN_SERVER, ip);
        return ip;
    }

    private int readPortMainServer(Properties properties)
            throws ReadingPropertiesException {
        String portStr = properties.getProperty(PROPERTY_PORT_MAIN_SERVER);
        if (portStr == null) {
            throw new ReadingPropertiesException("Property '" +
                    PROPERTY_PORT_MAIN_SERVER + "' isn't defined");
        }

        int port;
        try {
            port = Integer.parseInt(portStr);
        } catch (NumberFormatException e) {
            throw new ReadingPropertiesException("can't parse property '"
                    + PROPERTY_PORT_MAIN_SERVER + "'");
        }

        logger.info("{}: {}", PROPERTY_PORT_MAIN_SERVER, port);
        return port;
    }

    /// Nullable
    private String readIpSecondServer(Properties properties)
            throws ReadingPropertiesException {
        String ip = properties.getProperty(PROPERTY_IP_SECOND_SERVER);
        if (Objects.isNull(ip) || ip.isEmpty()) {
            logger.warn("Property '"
                    + PROPERTY_IP_SECOND_SERVER + "' isn't defined");
        } else {
            logger.info("{}: {}", PROPERTY_IP_SECOND_SERVER, ip);
        }
        return ip;
    }

    /// Nullable
    private Integer readPortSecondServer(Properties properties)
            throws ReadingPropertiesException {
        String portStr = properties.getProperty(PROPERTY_PORT_SECOND_SERVER);
        if (portStr == null) {
            logger.warn("Property '" + PROPERTY_PORT_SECOND_SERVER
                    + "' isn't defined");
            return null;
        }

        int port;
        try {
            port = Integer.parseInt(portStr);
        } catch (NumberFormatException e) {
            throw new ReadingPropertiesException("can't parse property '"
                    + PROPERTY_PORT_SECOND_SERVER + "'");
        }

        logger.info("{}: {}", PROPERTY_PORT_SECOND_SERVER, port);
        return port;
    }

    /// Interval between sending data (pause between http-requests) in seconds
    private int readPauseBetweenRequests(Properties properties)
            throws ReadingPropertiesException {
        int pauseBetweenRequests;
        try {
            pauseBetweenRequests = Integer.parseInt(
                    properties.getProperty(PROPERTY_PAUSE_BETWEEN_REQUESTS));
        } catch (NumberFormatException e) {
            throw new ReadingPropertiesException(
                    "can't read or parse property '" +
                            PROPERTY_PAUSE_BETWEEN_REQUESTS + "'", e);
        }

        logger.info("Interval between sending data (property '"
                + PROPERTY_PAUSE_BETWEEN_REQUESTS + "' ): {} seconds",
                pauseBetweenRequests);
        return pauseBetweenRequests;
    }

    private void readDeviceProperties(Properties properties, Params params)
            throws ReadingPropertiesException {
        params.setDeviceName(readDeviceName(properties));
        params.setAutonomousClock(readAutonomousClock(properties));
    }

    private String readDeviceName(Properties properties)
            throws ReadingPropertiesException {
        String deviceName = properties.getProperty(PROPERTY_DEVICE_NAME);
        if (Objects.isNull(deviceName) || deviceName.isEmpty()) {
            throw new ReadingPropertiesException("Property '" +
                    PROPERTY_DEVICE_NAME + "' isn't defined");
        } else {
            logger.info("{}: {}", PROPERTY_DEVICE_NAME, deviceName);
        }
        return deviceName;
    }

    private boolean readAutonomousClock(Properties properties)
            throws ReadingPropertiesException {
        String autonomousClockStr = properties.getProperty(
                PROPERTY_AUTONOMOUS_CLOCK);
        if (autonomousClockStr == null) {
            logger.warn("Property '" + PROPERTY_AUTONOMOUS_CLOCK +
                    "' isn't defined. So it has been set to false");
            return false;
        }

        boolean autonomousClock;
        try {
            autonomousClock = Boolean.parseBoolean(autonomousClockStr);
        } catch (NumberFormatException e) {
            throw new ReadingPropertiesException("can't parse property '"
                    + PROPERTY_AUTONOMOUS_CLOCK + "'");
        }

        logger.info("{}: {}", PROPERTY_AUTONOMOUS_CLOCK, autonomousClock);
        return autonomousClock;
    }
}