package FogOSMessage;

import FlexID.FlexID;
import FogOSCore.FogOSBroker;

public class StatusMessage extends Message {

    public StatusMessage() {
        super(MessageType.STATUS);
        init();
    }

    public StatusMessage(FlexID deviceID) {
        super(MessageType.STATUS, deviceID);
        init();
    }

    @Override
    public void init() {

    }

    @Override
    public void test(FogOSBroker broker) {

    }
}
