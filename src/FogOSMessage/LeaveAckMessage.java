package FogOSMessage;

import FlexID.FlexID;
import FogOSCore.FogOSBroker;

public class LeaveAckMessage extends Message {

    public LeaveAckMessage() {
        super(MessageType.LEAVE_ACK);
        init();
    }

    public LeaveAckMessage(FlexID deviceID) {
        super(MessageType.LEAVE_ACK, deviceID);
        init();
    }

    @Override
    public void init() {

    }

    @Override
    public void test(FogOSBroker broker) {

    }
}
