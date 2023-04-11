package tddmicroexercises.telemetrysystem.telemetryclient.communication;

public interface TelemetryMessageCommunicator {
    void send(String message);
    String receive();
}
