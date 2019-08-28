package FogOSMessage;

import FlexID.FlexID;
import FogOSControl.Core.FogOSBroker;

public class RegisterMessage extends Message {

    public RegisterMessage() {
        super(MessageType.REGISTER);
    }

    public RegisterMessage(FlexID deviceID) {
        super(MessageType.REGISTER, deviceID);
    }

    @Override
    public Message send(FogOSBroker broker, FlexID deviceID) {
        return null;
    }
}
