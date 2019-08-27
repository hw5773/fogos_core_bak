package FogOSMessage;

import FlexID.FlexID;
import FogOSControl.Core.FogOSBroker;

public class JoinAckMessage extends Message {

    public JoinAckMessage() {
        super(MessageType.JOIN_ACK);
    }

    public JoinAckMessage(FlexID deviceID) {
        super(MessageType.JOIN_ACK, deviceID);
    }

    @Override
    public Message send(FogOSBroker broker) {
        return null;
    }
}
