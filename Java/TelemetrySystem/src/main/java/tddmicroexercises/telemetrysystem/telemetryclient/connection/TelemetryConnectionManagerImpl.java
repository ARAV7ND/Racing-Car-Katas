package tddmicroexercises.telemetrysystem.telemetryclient.connection;

import java.util.Random;

public class TelemetryConnectionManagerImpl implements TelemetryConnectionManager {

    private final Random connectionEventsSimulator = new Random(42);
    private boolean onlineStatus;

    @Override
    public void connect(String telemetryServerConnectionString) {
        if (telemetryServerConnectionString == null || "".equals(telemetryServerConnectionString)) {
            throw new IllegalArgumentException();
        }

        // simulate the operation on a real modem
        boolean success = connectionEventsSimulator.nextInt(10) <= 8;

        onlineStatus = success;
    }

    @Override
    public void disconnect() {
        onlineStatus = false;
    }

    @Override
    public boolean getConnectionStatus() {
        return onlineStatus;
    }


}
