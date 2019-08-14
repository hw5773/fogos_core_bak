package FogOSControl.Core;

public enum CommunicationProtocol {
    MQTT("mqtt"),
    HTTP("http");

    String protocolName;

    CommunicationProtocol(String name) {
        this.protocolName = name;
    }

    String getName() {
        return protocolName;
    }
}
