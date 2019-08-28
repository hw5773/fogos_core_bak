package FogOSMessage;

import FlexID.FlexID;
import FogOSControl.Core.FogOSBroker;

public class UpdateAckMessage extends Message {

    public UpdateAckMessage() {
        super(MessageType.UPDATE_ACK);
    }

    public UpdateAckMessage(FlexID deviceID) {
        super(MessageType.UPDATE_ACK, deviceID);
    }

    @Override
    public Message send(FogOSBroker broker, FlexID deviceID) {
        return null;
    }
}
