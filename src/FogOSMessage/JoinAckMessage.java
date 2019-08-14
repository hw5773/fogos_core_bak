package FogOSMessage;

import FogOSControl.Core.FogOSBroker;
import versatile.flexid.FlexID;

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
