package FogOSMessage;

import FlexID.FlexID;
import FogOSCore.FogOSBroker;

public class StatusAckMessage extends Message {

    public StatusAckMessage() {
        super(MessageType.STATUS_ACK);
        init();
    }

    public StatusAckMessage(FlexID deviceID) {
        super(MessageType.STATUS_ACK, deviceID);
        init();
    }

    @Override
    public void init() {

    }

    @Override
    public void test(FogOSBroker broker) {

    }
}
