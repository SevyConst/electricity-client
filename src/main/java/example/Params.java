package example;

import java.net.URI;

public class Params {
    private URI uriMain;
    private URI uriSecond;
    private Integer pauseBetweenRequests;   /// Seconds
    private String deviceName;
    private boolean autonomousClock;

    public URI getUriMain() {
        return uriMain;
    }

    public void setUriMain(URI uriMain) {
        this.uriMain = uriMain;
    }

    public URI getUriSecond() {
        return uriSecond;
    }

    public void setUriSecond(URI uriSecond) {
        this.uriSecond = uriSecond;
    }

    public Integer getPauseBetweenRequests() {
        return pauseBetweenRequests;
    }

    public void setPauseBetweenRequests(Integer pauseBetweenRequests) {
        this.pauseBetweenRequests = pauseBetweenRequests;
    }

    public String getDeviceName() {
        return deviceName;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }

    public boolean isAutonomousClock() {
        return autonomousClock;
    }

    public void setAutonomousClock(boolean autonomousClock) {
        this.autonomousClock = autonomousClock;
    }
}
