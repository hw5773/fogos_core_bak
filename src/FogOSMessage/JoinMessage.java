package FogOSMessage;

import FlexID.FlexID;
import FogOSControl.Core.FogOSBroker;

public class JoinMessage extends Message {

    public JoinMessage() {
        super(MessageType.JOIN);
    }

    public JoinMessage(FlexID deviceID) {
        super(MessageType.JOIN, deviceID);
    }

    @Override
    public Message send(FogOSBroker broker, FlexID deviceID) {
        return null;
    }
}
