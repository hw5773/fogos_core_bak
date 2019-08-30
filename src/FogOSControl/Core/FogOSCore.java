package FogOSControl.Core;

import FogOSMessage.*;
import FlexID.FlexID;
import FlexID.Locator;
import FlexID.InterfaceType;

import java.util.LinkedList;
import java.util.logging.Level;

public class FogOSCore {
    private final String cloudName = "www.versatile-cloud.com";
    private final int cloudPort = 3333;
    private LinkedList<FogOSBroker> brokers;
    private FogOSBroker broker;
    private FlexID deviceID;
    private ContentStore store;
    private static final String TAG = "FogOSCore";

    public FogOSCore() {
        java.util.logging.Logger.getLogger(TAG).log(Level.INFO, "Start: Initialize FogOSCore");
        retrieveBrokerList();
        broker = findBestFogOSBroker();
        store = new ContentStore();

        deviceID = FlexID.generateDeviceID();
        initSubscribe(deviceID);
        java.util.logging.Logger.getLogger(TAG).log(Level.INFO, "Finish: Initialize FogOSCore");
    }

    // Access to the cloud to get the list of the FogOS brokers
    void retrieveBrokerList() {
        java.util.logging.Logger.getLogger(TAG).log(Level.INFO, "Start: retrieveBrokerList()");
        // TODO: Implement this function
        // request the list to the cloud

        // parse the response and add brokers to "brokers"
        brokers = new LinkedList<FogOSBroker>();
        java.util.logging.Logger.getLogger(TAG).log(Level.INFO, "Finish: retrieveBrokerList()");
    }

    // Ping test and select the best FogOS broker
    FogOSBroker findBestFogOSBroker()
    {
        java.util.logging.Logger.getLogger(TAG).log(Level.INFO, "Start: findBestFogOSBroker()");

        // TODO: Implement this function

        java.util.logging.Logger.getLogger(TAG).log(Level.INFO, "Finish: findBestFogOSBroker()");
        return null;
    }

    // Initialize subscriptions with the selected broker
    void initSubscribe(FlexID deviceID) {
        java.util.logging.Logger.getLogger(TAG).log(Level.INFO, "Start: initSubscribe()");
        byte[] joinAckTopic = MessageType.JOIN_ACK.getTopicWithDeviceID(deviceID);
        byte[] leaveAckTopic = MessageType.LEAVE_ACK.getTopicWithDeviceID(deviceID);
        byte[] statusAckTopic = MessageType.STATUS_ACK.getTopicWithDeviceID(deviceID);
        byte[] registerAckTopic = MessageType.REGISTER_ACK.getTopicWithDeviceID(deviceID);
        byte[] updateAckTopic = MessageType.UPDATE_ACK.getTopicWithDeviceID(deviceID);
        byte[] mapUpdateAckTopic = MessageType.MAP_UPDATE_ACK.getTopicWithDeviceID(deviceID);

        // TODO: Implement this function.

        java.util.logging.Logger.getLogger(TAG).log(Level.INFO, "Finish: initSubscribe()");
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
                break;
            case REPLY:
                msg = new ReplyMessage(deviceID);
                break;
            case REQUEST:
                msg = new RequestMessage(deviceID);
                break;
            case RESPONSE:
                msg = new ResponseMessage(deviceID);
                break;
        }

        return msg;
    }

    public Message sendMessage(Message msg) {
        java.util.logging.Logger.getLogger(TAG).log(Level.INFO, "Start: sendMessage(): MessageType: " + msg.getMessageType().toString());

        /* Test Returns */
        if (msg.getMessageType() == MessageType.QUERY && msg.getValueByAttr("keywords").equals("test")) {
            java.util.logging.Logger.getLogger(TAG).log(Level.INFO, "Make a test list started.");
            ReplyMessage replyMessage;
            FlexID id;
            replyMessage = (ReplyMessage) generateMessage(MessageType.REPLY);
            id = new FlexID("test");
            java.util.logging.Logger.getLogger(TAG).log(Level.INFO, "id 1: " + id + " / ID 1: " + new String(id.getIdentity()));
            replyMessage.addReplyEntry("환호하는 손흥민", "손흥민이 한독전에서 골을 넣고 환호하고 있다.", id);
            id = new FlexID("test");
            java.util.logging.Logger.getLogger(TAG).log(Level.INFO, "id 2: " + id + " / ID 2: " + new String(id.getIdentity()));
            replyMessage.addReplyEntry("기뻐하는 김영권 영상", "온국민을 환호하게 만든 김영권의 첫골을 다시보자.", id);
            id = new FlexID("test");
            java.util.logging.Logger.getLogger(TAG).log(Level.INFO, "id 3: " + id + " / ID 3: " + new String(id.getIdentity()));
            replyMessage.addReplyEntry("좌절에 빠진 독일 팬들", "예기치 못한 패배에 독일 팬들은 모두 울상을 짓고 있다.", id);
            java.util.logging.Logger.getLogger(TAG).log(Level.INFO, "Make a test list finished.");
            java.util.logging.Logger.getLogger(TAG).log(Level.INFO, "Finish: sendMessage()");
            return replyMessage;
        } else if (msg.getMessageType() == MessageType.REQUEST) {
            RequestMessage requestMessage;
            requestMessage = (RequestMessage) msg;
            java.util.logging.Logger.getLogger(TAG).log(Level.INFO, "Make a test response message started.");
            java.util.logging.Logger.getLogger(TAG).log(Level.INFO, "Peer ID: " + new String(requestMessage.getPeerID().getIdentity()));
            ResponseMessage responseMessage;
            Locator locator = new Locator(InterfaceType.WIFI, "192.168.0.128", 3333);
            responseMessage = (ResponseMessage) generateMessage(MessageType.RESPONSE);
            responseMessage.setPeerID(new FlexID(msg.getValueByAttr("id")));
            responseMessage.getPeerID().setLocator(locator);

            java.util.logging.Logger.getLogger(TAG).log(Level.INFO, "Make a test response message finished.");
            return responseMessage;
        }
        java.util.logging.Logger.getLogger(TAG).log(Level.INFO, "Finish: sendMessage()");
        return msg.send(broker, deviceID);
    }
}
