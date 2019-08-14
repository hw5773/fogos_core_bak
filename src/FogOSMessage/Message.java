package FogOSMessage;
import FogOSControl.Core.CommunicationProtocol;
import FogOSControl.Core.FogOSBroker;
import versatile.flexid.AttrValuePairs;
import versatile.flexid.FlexID;

public abstract class Message {
    MessageType messageType;
    FlexID deviceID;
    AttrValuePairs body;

    public Message(FlexID deviceID) {
        this.messageType = null;
        this.deviceID = deviceID;
        body = null;
    }

    public Message(MessageType messageType) {
        this.messageType = messageType;
        this.deviceID = null;
        this.body = new AttrValuePairs();
    }

    public Message(MessageType messageType, FlexID deviceID) {
        this.messageType = messageType;
        this.deviceID = deviceID;
        this.body = new AttrValuePairs();
    }

    public MessageType getMessageType() {
        return messageType;
    }

    public void setMessageType(MessageType messageType) {
        this.messageType = messageType;
    }

    public FlexID getDeviceID() {
        return deviceID;
    }

    public void setDeviceID(FlexID deviceID) {
        this.deviceID = deviceID;
    }

    public void addAttrValuePair(String attr, String value) {
        this.body.addAttrValuePair(attr, value);
    }

    public String getValueByAttr(String attr) {
        return this.body.getValueByAttr(attr);
    }

    // TODO: Implement this function or Abstract?
    public abstract Message send(FogOSBroker broker);
}
