package FogOSMessage;

import FlexID.FlexID;
import FogOSControl.Core.FogOSBroker;

public class UpdateMessage extends Message {

    public UpdateMessage() {
        super(MessageType.UPDATE);
    }

    public UpdateMessage(FlexID deviceID) {
        super(MessageType.UPDATE, deviceID);
    }

    @Override
    public Message send(FogOSBroker broker, FlexID deviceID) {
        return null;
    }
}
