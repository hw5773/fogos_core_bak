package FogOSMessage;

import FlexID.FlexID;
import FogOSControl.Core.FogOSBroker;

public class StatusAckMessage extends Message {

    public StatusAckMessage() {
        super(MessageType.STATUS_ACK);
    }

    public StatusAckMessage(FlexID deviceID) {
        super(MessageType.STATUS_ACK, deviceID);
    }

    @Override
    public Message send(FogOSBroker broker, FlexID deviceID) {
        return null;
    }
}
