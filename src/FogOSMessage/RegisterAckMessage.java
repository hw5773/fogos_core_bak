package FogOSMessage;

import FlexID.FlexID;
import FogOSCore.FogOSBroker;

public class RegisterAckMessage extends Message {

    public RegisterAckMessage() {
        super(MessageType.REGISTER_ACK);
        init();
    }

    public RegisterAckMessage(FlexID deviceID) {
        super(MessageType.REGISTER_ACK, deviceID);
        init();
    }

    @Override
    public void init() {

    }

    @Override
    public void test(FogOSBroker broker) {

    }
}
