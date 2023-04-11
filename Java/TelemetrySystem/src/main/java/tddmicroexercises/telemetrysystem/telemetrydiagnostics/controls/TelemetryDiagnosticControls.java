package tddmicroexercises.telemetrysystem.telemetrydiagnostics.controls;

public interface TelemetryDiagnosticControls {
    String getDiagnosticInfo();
    void setDiagnosticInfo(String diagnosticInfo);
    void checkTransmission() throws Exception;
}
