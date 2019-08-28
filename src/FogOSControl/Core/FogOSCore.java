package FogOSControl.Core;

import FogOSMessage.*;
import FlexID.FlexID;

import java.util.LinkedList;

public class FogOSCore {
    private final String cloudName = "www.versatile-cloud.com";
    private final int cloudPort = 3333;
    private LinkedList<FogOSBroker> brokers;
    private FogOSBroker broker;
    private FlexID deviceID;
    private ContentStore store;

    public FogOSCore() {
        retrieveBrokerList();
        broker = findBestFogOSBroker();
        store = new ContentStore();

        deviceID = FlexID.generateDeviceID();
        initSubscribe(deviceID);
    }

    // Access to the cloud to get the list of the FogOS brokers
    void retrieveBrokerList() {
        // request the list to the cloud

        // parse the response and add brokers to "brokers"
        brokers = new LinkedList<FogOSBroker>();
    }

    // Ping test and select the best FogOS broker
    FogOSBroker findBestFogOSBroker()
    {
        return null;
    }

    // Initialize subscriptions with the selected broker
    void initSubscribe(FlexID deviceID) {
        byte[] joinAckTopic = MessageType.JOIN_ACK.getTopicWithDeviceID(deviceID);
        byte[] leaveAckTopic = MessageType.LEAVE_ACK.getTopicWithDeviceID(deviceID);
        byte[] statusAckTopic = MessageType.STATUS_ACK.getTopicWithDeviceID(deviceID);
        byte[] registerAckTopic = MessageType.REGISTER_ACK.getTopicWithDeviceID(deviceID);
        byte[] updateAckTopic = MessageType.UPDATE_ACK.getTopicWithDeviceID(deviceID);
        byte[] mapUpdateAckTopic = MessageType.MAP_UPDATE_ACK.getTopicWithDeviceID(deviceID);
    }

    public Message generateMessage(MessageType messageType) {
        Message msg = null;
        switch (messageType) {
            case JOIN:
                msg = new JoinMessage(deviceID);
                break;
            case JOIN_ACK:
                msg = new JoinAckMessage(deviceID);
                break;
            case STATUS:

                break;
            case STATUS_ACK:
                break;
            case LEAVE:
                break;
            case LEAVE_ACK:
                break;
            case REGISTER:
                break;
            case REGISTER_ACK:
                break;
            case UPDATE:
                break;
            case UPDATE_ACK:
                break;
            case MAP_UPDATE:
                break;
            case MAP_UPDATE_ACK:
                break;
            case QUERY:
                msg = new QueryMessage(deviceID);
            case REPLY:
                break;
            case REQUEST:
                break;
            case RESPONSE:
                break;
        }

        return msg;
    }

    public Message sendMessage(Message msg) {
        return msg.send(broker, null);
    }
}
