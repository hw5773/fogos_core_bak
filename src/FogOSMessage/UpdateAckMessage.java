package FogOSMessage;

import FlexID.FlexID;
import FogOSCore.FogOSBroker;

public class UpdateAckMessage extends Message {

    public UpdateAckMessage() {
        super(MessageType.UPDATE_ACK);
        init();
    }

    public UpdateAckMessage(FlexID deviceID) {
        super(MessageType.UPDATE_ACK, deviceID);
        init();
    }

    @Override
    public void init() {

    }

    @Override
    public void test(FogOSBroker broker) {

    }
}
