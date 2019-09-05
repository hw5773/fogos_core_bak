package FogOSControl.Core;

public class FogOSBroker {
    private String name;
    private int port;
    private CommunicationProtocol protocol;

    public FogOSBroker() {
        this.name = null;
        this.port = -1;
        this.protocol = CommunicationProtocol.MQTT;
    }

    public FogOSBroker(String name) {
        this.name = name;
        this.port = 1883;
        this.protocol = CommunicationProtocol.MQTT;
    }

    public FogOSBroker(String name, int port) {
        this.name = name;
        this.port = port;
        this.protocol = CommunicationProtocol.MQTT;
    }

    public FogOSBroker(String name, int port, CommunicationProtocol protocol) {
        this.name = name;
        this.port = port;
        this.protocol = protocol;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public CommunicationProtocol getProtocol() {
        return protocol;
    }

    public void setProtocol(CommunicationProtocol protocol) {
        this.protocol = protocol;
    }
}
