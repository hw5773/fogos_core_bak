package FogOSMessage;

import FlexID.FlexID;
import FogOSCore.FogOSBroker;

public class StatusMessage extends Message {

    public StatusMessage() {
        super(MessageType.STATUS);
    }

    public StatusMessage(FlexID deviceID) {
        super(MessageType.STATUS, deviceID);
    }

    @Override
    public Message send(FogOSBroker broker, FlexID deviceID) {
        return null;
    }
}
