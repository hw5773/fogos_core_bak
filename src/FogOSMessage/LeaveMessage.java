package FogOSMessage;

import FlexID.FlexID;
import FogOSCore.FogOSBroker;

public class LeaveMessage extends Message {

    public LeaveMessage() {
        super(MessageType.LEAVE);
    }

    public LeaveMessage(FlexID deviceID) {
        super(MessageType.LEAVE, deviceID);
    }

    @Override
    public Message send(FogOSBroker broker, FlexID deviceID) {
        return null;
    }
}
