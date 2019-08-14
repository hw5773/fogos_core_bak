package FogOSMessage;

import FogOSControl.Core.FogOSBroker;
import versatile.flexid.FlexID;

public class JoinMessage extends Message {

    public JoinMessage() {
        super(MessageType.JOIN);
    }

    public JoinMessage(FlexID deviceID) {
        super(MessageType.JOIN, deviceID);
    }

    @Override
    public Message send(FogOSBroker broker) {
        return null;
    }
}
