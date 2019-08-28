package FogOSMessage;

import FlexID.FlexID;
import FogOSControl.Core.FogOSBroker;

public class LeaveAckMessage extends Message {

    public LeaveAckMessage() {
        super(MessageType.LEAVE_ACK);
    }

    public LeaveAckMessage(FlexID deviceID) {
        super(MessageType.LEAVE_ACK, deviceID);
    }

    @Override
    public Message send(FogOSBroker broker, FlexID deviceID) {
        return null;
    }
}
