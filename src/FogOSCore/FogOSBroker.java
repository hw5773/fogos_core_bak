package FogOSCore;

import org.eclipse.paho.client.mqttv3.MqttClient;

public class FogOSBroker {
    private String name;
    private int port;
    private CommunicationProtocol protocol;
    private MqttClient mqttClient;

    public FogOSBroker() {
        this.name = null;
        this.port = -1;
        this.protocol = CommunicationProtocol.MQTT;
        this.mqttClient = null;
    }

    public FogOSBroker(String name) {
        this.name = name;
        this.port = 1883;
        this.protocol = CommunicationProtocol.MQTT;
        this.mqttClient = null;
    }

    public FogOSBroker(String name, int port) {
        this.name = name;
        this.port = port;
        this.protocol = CommunicationProtocol.MQTT;
        this.mqttClient = null;
    }

    public FogOSBroker(String name, int port, CommunicationProtocol protocol) {
        this.name = name;
        this.port = port;
        this.protocol = protocol;
        this.mqttClient = null;
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

    public MqttClient getMqttClient() {
        return mqttClient;
    }

    public void setMqttClient(MqttClient mqttClient) {
        this.mqttClient = mqttClient;
    }
}
