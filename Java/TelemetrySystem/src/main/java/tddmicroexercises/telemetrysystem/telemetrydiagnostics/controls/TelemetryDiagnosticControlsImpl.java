package tddmicroexercises.telemetrysystem.telemetrydiagnostics.controls;

import tddmicroexercises.telemetrysystem.telemetryclient.communication.TelemetryMessageCommunicator;
import tddmicroexercises.telemetrysystem.telemetryclient.communication.TelemetryMessageCommunicatorImpl;
import tddmicroexercises.telemetrysystem.telemetryclient.connection.TelemetryConnectionManager;

public class TelemetryDiagnosticControlsImpl implements TelemetryDiagnosticControls {

    private static final String DiagnosticChannelConnectionString = "*111#";

    private final TelemetryMessageCommunicator telemetryMessageCommunicator;

    private final TelemetryConnectionManager telemetryConnectionManager;

    public TelemetryDiagnosticControlsImpl(TelemetryMessageCommunicator telemetryMessageCommunicator, TelemetryConnectionManager telemetryConnectionManager) {
        this.telemetryMessageCommunicator = telemetryMessageCommunicator;
        this.telemetryConnectionManager = telemetryConnectionManager;
    }

    private String diagnosticInfo = "";

    @Override
    public String getDiagnosticInfo() {
        return diagnosticInfo;
    }

    @Override
    public void setDiagnosticInfo(String diagnosticInfo) {
        this.diagnosticInfo = diagnosticInfo;
    }

    @Override
    public void checkTransmission() throws Exception {
        diagnosticInfo = "";

        telemetryConnectionManager.disconnect();
        int retryLeft = 3;
        while (!telemetryConnectionManager.getConnectionStatus() && retryLeft > 0) {
            telemetryConnectionManager.connect(DiagnosticChannelConnectionString);
            retryLeft -= 1;
        }

        if (!telemetryConnectionManager.getConnectionStatus()) {
            throw new Exception("Unable to connect.");
        }

        telemetryMessageCommunicator.send(TelemetryMessageCommunicatorImpl.DIAGNOSTIC_MESSAGE);
        diagnosticInfo = telemetryMessageCommunicator.receive();
    }
}
