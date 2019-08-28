package FogOSMessage;

import FlexID.FlexID;
import FogOSControl.Core.FogOSBroker;

public class RegisterAckMessage extends Message {

    public RegisterAckMessage() {
        super(MessageType.REGISTER_ACK);
    }

    public RegisterAckMessage(FlexID deviceID) {
        super(MessageType.REGISTER_ACK, deviceID);
    }

    @Override
    public Message send(FogOSBroker broker, FlexID deviceID) {
        return null;
    }
}
