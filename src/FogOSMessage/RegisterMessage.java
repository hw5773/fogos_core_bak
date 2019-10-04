package FogOSMessage;

import FlexID.FlexID;
import FogOSCore.FogOSBroker;

public class RegisterMessage extends Message {

    public RegisterMessage() {
        super(MessageType.REGISTER);
        init();
    }

    public RegisterMessage(FlexID deviceID) {
        super(MessageType.REGISTER, deviceID);
        init();
    }

    @Override
    public void init() {

    }

    @Override
    public void test(FogOSBroker broker) {

    }
}
