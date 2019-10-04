package FogOSMessage;

import FlexID.FlexID;
import FogOSCore.FogOSBroker;

public class JoinAckMessage extends Message {

    public JoinAckMessage() {
        super(MessageType.JOIN_ACK);
        init();
    }

    public JoinAckMessage(FlexID deviceID) {
        super(MessageType.JOIN_ACK, deviceID);
        init();
    }

    @Override
    public void init() {

    }

    @Override
    public void test(FogOSBroker broker) {

    }
}
