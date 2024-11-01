package example;

public class Params {
    private String ipMainServer;
    private int portMainServer;
    private String ipSecondServer;  /// Nullable
    private Integer portSecondServer;  /// Nullable
    private Integer pauseBetweenRequests;   /// Seconds
    private String deviceName;
    private boolean autonomousClock;

    public String getIpMainServer() {
        return ipMainServer;
    }

    public void setIpMainServer(String ipMainServer) {
        this.ipMainServer = ipMainServer;
    }

    public int getPortMainServer() {
        return portMainServer;
    }

    public void setPortMainServer(int portMainServer) {
        this.portMainServer = portMainServer;
    }

    public String getIpSecondServer() {
        return ipSecondServer;
    }

    public void setIpSecondServer(String ipSecondServer) {
        this.ipSecondServer = ipSecondServer;
    }

    public Integer getPortSecondServer() {
        return portSecondServer;
    }

    public void setPortSecondServer(Integer portSecondServer) {
        this.portSecondServer = portSecondServer;
    }

    public Integer getPauseBetweenRequests() {
        return pauseBetweenRequests;
    }

    public void pauseBetweenRequests(Integer requestPauseSeconds) {
        this.pauseBetweenRequests = requestPauseSeconds;
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
