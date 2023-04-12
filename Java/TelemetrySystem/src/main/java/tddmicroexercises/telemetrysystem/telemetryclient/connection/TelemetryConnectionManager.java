package tddmicroexercises.telemetrysystem.telemetryclient.connection;

public interface TelemetryConnectionManager {
    void connect(String telemetryServerConnectionString);
    void disconnect();
    boolean getConnectionStatus();
}